# 协同写作系统

一个基于Vue3的在线协同文档编辑系统，支持实时协作编辑、版本历史、知识库管理等功能。

## 技术栈

- 前端框架：Vue 3
- 构建工具：Vite
- 语言：TypeScript
- 状态管理：Pinia
- UI框架：Element Plus
- 路由：Vue Router
- 样式处理：SASS

## 📁 项目结构

### 页面视图层 (src/views/)
```tree
views/
├── Home.vue               # 知识库首页
│   ├── 目录树展示
│   ├── 最近访问
│   └── 新建文档入口
├── KnowledgeBase.vue      # 知识库管理页面
│   ├── 文档组织
│   └── 分类管理
├── DocumentEditor.vue     # 文档编辑器页面
│   ├── 协同编辑
│   ├── 工具栏
│   └── 实时预览
├── Login.vue             # 用户登录页面
└── Register.vue          # 用户注册页面
```

### 组件层 (src/components/)
```tree
components/
├── common/               # 公共组件
│   ├── Layout.vue       # 整体布局
│   ├── Header.vue       # 顶部导航
│   └── Sidebar.vue      # 侧边栏
├── knowledge/           # 知识库组件
│   ├── KnowledgeTree.vue    # 知识树组件
│   ├── KnowledgeList.vue    # 知识列表
│   └── DocumentList.vue      # 文档列表
└── editor/             # 编辑器组件
    └── Editor.vue        # UmoEditor组件
    ├── CollaborativeEditor.vue   # 协同编辑器
    ├── CommentSystem.vue         # 评论系统
    └── VersionHistory.vue        # 版本历史
```

### 状态管理 (src/stores/)
```tree
stores/
├── user.ts         # 用户状态管理
│   ├── 登录状态
│   ├── 用户信息
│   └── 权限控制
├── knowledge.ts    # 知识库状态管理
│   ├── 目录结构
│   └── 文档分类
└── document.ts     # 文档状态管理
    ├── 编辑状态
    ├── 历史版本
    └── 协同状态
```

### API接口层 (src/api/)
```tree
api/
├── request.ts      # HTTP请求配置
│   ├── Axios实例
│   ├── 请求拦截器
│   └── 响应拦截器
├── user.ts         # 用户接口
│   ├── 登录注册
│   └── 用户信息
├── knowledge.ts    # 知识库接口
│   ├── 目录操作
│   └── 分类管理
└── document.ts     # 文档接口
    ├── 创建编辑
    ├── 协同操作
    └── 导入导出
```

### 工具和配置
```tree
├── router/             # 路由配置
│   └── index.ts       # 包含权限守卫
├── types/             # TypeScript类型
│   └── index.ts       # 全局类型定义
├── utils/             # 工具函数
│   └── index.ts       # 通用工具方法
└── styles/            # 样式文件
    ├── variables.scss # SCSS变量
    └── common.scss    # 公共样式
```


## 🚀 快速开始


### 安装和运行

1. **克隆项目**
```bash
git clone https://gitee.com/your-username/project-name.git
cd project-name
```

2. **安装依赖**
```bash
cd frontend
npm install
# 或
yarn install
```

3. **启动开发服务器**
```bash
npm run dev
# 或
yarn dev
```

4. **构建生产版本**
```bash
npm run build
# 或
yarn build
```

## 📝 开发规范

### 代码风格
- 使用ESLint进行代码检查
- 使用Prettier进行代码格式化
- 遵循Vue3组件命名规范

### Git提交规范
- feat: 新功能
- fix: 修复bug
- docs: 文档更新
- style: 代码格式
- refactor: 重构
- test: 测试
- chore: 构建过程或辅助工具的变动

## 浏览器支持

- Chrome (推荐)
- Firefox
- Safari
- Edge


## 许可证

[MIT License](LICENSE)
