// HTTP 请求工具配置
import axios from 'axios'
import type { InternalAxiosRequestConfig, AxiosResponse } from 'axios'

// 创建 axios 实例
const request = axios.create({
  baseURL: import.meta.env.VITE_API_BASE_URL || '/api',
  timeout: 10000,
  headers: {
    'Content-Type': 'application/json'
  }
})

// 请求拦截器
request.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    // 添加认证token等
    const token = localStorage.getItem('token')
    if (token) {
      config.headers.Authorization = `Bearer ${token}`
    }
    return config
  },
  (error: any) => {
    return Promise.reject(error)
  }
)

// 响应拦截器
request.interceptors.response.use(
  (response: AxiosResponse) => {
    const { data } = response
    
    // 如果后端返回的是标准格式 { code, message, data }
    if (data && typeof data === 'object' && 'code' in data) {
      const { code, message, data: businessData } = data
      
      // 业务成功状态码（通常是200或0）
      if (code === 200 || code === 0) {
        return businessData  // 直接返回业务数据
      }
      
      // 业务失败，抛出错误
      const error = new Error(message || '请求失败')
      error.name = 'BusinessError'
      ;(error as any).code = code
      return Promise.reject(error)
    }
    
    // 如果不是标准格式，直接返回原数据
    return data
  },
  (error: any) => {
    // HTTP状态码错误处理
    let message = '请求失败'
    
    if (error.response) {
      const { status, data } = error.response
      
      switch (status) {
        case 401:
          message = '未授权，请重新登录'
          // 可以在这里清除token并跳转到登录页
          localStorage.removeItem('token')
          break
        case 403:
          message = '拒绝访问'
          break
        case 404:
          message = '请求的资源不存在'
          break
        case 500:
          message = '服务器内部错误'
          break
        default:
          message = data?.message || `请求失败 (${status})`
      }
    } else if (error.request) {
      message = '网络连接失败'
    }
    
    console.error('请求错误:', message, error)
    return Promise.reject(new Error(message))
  }
)

export default request 