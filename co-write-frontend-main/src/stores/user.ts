// 用户状态管理 - Pinia Store
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({
    // 用户状态
  }),
  
  getters: {
    // 用户相关计算属性
  },
  
  actions: {
    // 用户相关操作
  }
}) 