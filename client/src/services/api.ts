import axios from 'axios';
import { refreshToken as refreshAuthToken } from './authService';

// Define a list of public paths that don't require authentication
const publicPaths = ['/auth/register', '/auth/login', '/auth/refreshToken'];

// Create an axios instance
const api = axios.create({
  baseURL: 'http://localhost:8181/api/v1',
});

// Add a request interceptor to include the token in headers for protected routes
api.interceptors.request.use(
  (config) => {
    if (config.url && !publicPaths.includes(config.url)) {
      const token = localStorage.getItem('accessToken');
      if (token) {
        config.headers.Authorization = `Bearer ${token}`;
      }
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

// Add a response interceptor to handle token refresh
api.interceptors.response.use(
  (response) => response, // Simply return the response if it's successful
  async (error) => {
    const originalRequest = error.config;

    // Check if the error is 401 and it's not a retry request
    if (error.response.status === 401 && !originalRequest._retry) {
      originalRequest._retry = true; // Mark this request as a retry

      try {
        const refreshToken = localStorage.getItem('refreshToken');
        if (!refreshToken) {
          // If no refresh token, redirect to login
          window.location.href = '/login';
          return Promise.reject(error);
        }

        // Call the refresh token endpoint
        const response = await refreshAuthToken(refreshToken);
        const { accessToken } = response.data;

        // Store the new token
        localStorage.setItem('accessToken', accessToken);

        // Update the authorization header and retry the original request
        api.defaults.headers.common['Authorization'] = `Bearer ${accessToken}`;
        originalRequest.headers['Authorization'] = `Bearer ${accessToken}`;

        return api(originalRequest);
      } catch (refreshError) {
        // If refresh token is invalid, clear storage and redirect to login
        localStorage.removeItem('accessToken');
        localStorage.removeItem('refreshToken');
        window.location.href = '/login';
        return Promise.reject(refreshError);
      }
    }

    return Promise.reject(error);
  }
);

export default api;
