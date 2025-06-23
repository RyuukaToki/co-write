// 用户相关 API 接口
import request from './request'
import type {
  LoginRequest,
  RegisterRequest,
  LogoutRequest,
  LoginResponse,
  RegisterResponse,
} from '@/types/index'

// 用户登录
export const login = (data: LoginRequest): Promise<LoginResponse> => {
  return request.post('/auth/login', data)
}

// 用户注册
export const register = (data: RegisterRequest): Promise<RegisterResponse> => {
  return request.post('/auth/register', data)
}

// 退出登录
export const logout = (data: LogoutRequest): Promise<void> => {
  return request.get('/auth/logout', { params: data })
}
