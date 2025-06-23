import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'

// https://vite.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve: {
    alias: {
      '@': resolve(__dirname, 'src')
    }
  },
  // 开发服务器配置
  server: {
    proxy: {
      // 将 /api 开头的请求代理到 Apifox Mock 服务器
      '/api': {
        target: 'http://127.0.0.1:4523/m1/6606893-6313127-default/', 
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, '') // 移除 /api 前缀
      }
    }
  }
})
