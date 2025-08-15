import api from './api';

interface AuthResponse {
  accessToken: string;
  refreshToken: string;
}

interface UserCredentials {
  username?: string;
  email?: string;
  password?: string;
}

export const register = (credentials: UserCredentials) => {
  return api.post('/auth/register', credentials);
};

export const login = (credentials: UserCredentials) => {
  return api.post<AuthResponse>('/auth/login', credentials);
};

export const refreshToken = (token: string) => {
  return api.post<AuthResponse>('/auth/refreshToken', { refreshToken: token });
};
