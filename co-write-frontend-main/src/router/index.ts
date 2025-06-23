// 路由配置
import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(),
  routes: [
    // 登录页面
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/Login.vue'),
      meta: { 
        title: '登录 - 富文本编辑器',
        hideNavigation: true 
      }
    },
    
    // 注册页面
    {
      path: '/register',
      name: 'Register',
      component: () => import('@/views/Register.vue'),
      meta: { 
        title: '注册 - 富文本编辑器',
        hideNavigation: true 
      }
    },

    // 主应用布局 - 包含侧边栏和顶部导航
    {
      path: '/',
      component: () => import('@/components/common/Layout.vue'),
      meta: { requiresAuth: true },
      children: [
        // 首页 - 知识库列表页面
        {
          path: '',
          name: 'Home',
          component: () => import('@/views/Home.vue'),
          meta: { 
            title: '首页',
          }
        },
        // 知识库详情页面（显示某个知识库下的所有文档）
        {
          path: 'knowledge/:knowledgeId',
          name: 'KnowledgeBase',
          component: () => import('@/views/KnowledgeBase.vue'),
          meta: { 
            title: '知识库', // 将作为父级面包屑
          },
          // 将文档编辑器作为子路由
          children: [
            {
              path: ':documentId',
              name: 'DocumentEditor',
              component: () => import('@/views/DocumentEditor.vue'),
              meta: {
                title: '文档', // 将作为子级面包屑
              }
            }
          ]
        },

        // 为共享文档创建新的路由规则
        {
          path: 'shared',
          // 使用一个简单的内联模板作为父路由的容器
          component:() => import('@/views/SharedEditor.vue'),
          meta: {
            title: '共享文档' // 面包屑的父级
          },
          children: [
            {
              path: ':documentId',
              name: 'SharedDocument',
              component: () => import('@/views/DocumentEditor.vue'),
              meta: {
                title: '文档' // 动态部分，会显示为文档标题
              }
            }
          ]
        },
      ]
    },

    // 404 页面
    {
      path: '/404',
      name: 'NotFound',
      component: () => import('@/views/404.vue'),
      meta: { 
        title: '页面未找到',
        hideNavigation: true 
      }
    },

    // 捕获所有未匹配的路由
    {
      path: '/:pathMatch(.*)*',
      redirect: '/404'
    }
  ]
})

// 路由守卫
router.beforeEach((to, _from, next) => {
  // 设置页面标题
  if (to.meta.title) {
    document.title = to.meta.title as string
  }
  
  // 开发环境暂时禁用认证检查
  if (import.meta.env.DEV) {
    next()
    return
  }
  
  // 生产环境的认证检查
  const token = localStorage.getItem('token')
  if (to.meta.requiresAuth && !token) {
    next('/login')
  } else if ((to.name === 'Login' || to.name === 'Register') && token) {
    // 已登录用户访问登录/注册页面时，重定向到首页
    next('/')
  } else {
    next()
  }
})

// 路由后置守卫 - 可用于页面加载完成后的处理
router.afterEach((to) => {
  // 可以在这里添加页面访问统计等逻辑
  console.log(`导航到: ${to.path}`)
})

export default router 