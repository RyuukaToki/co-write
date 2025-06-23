// TypeScript 类型定义

// ============= 基础类型 =============

// 用户类型
export interface User {
  id: string
  username: string
  email: string
  avatar_url: string
}

// 知识库类型
export interface KnowledgeBase {
  id: string
  name: string
  cover_url: string
  document_ids: string[]
}

// ============= 用户相关类型 =============

export interface LoginRequest {
  username: string
  password: string
  email: string
}

export interface RegisterRequest {
  username: string
  password: string
  email: string
}


export interface LoginResponse {
  token: string
  user: User
}

export interface RegisterResponse {
  token: string
  user: User
}

export interface LogoutRequest {
  token: string
}

// ============= 知识库相关类型 =============


export interface CreateKnowledgeRequest {
  name: string
  cover_url: string
}

export interface GetKnowledgeListResponse {
  knowledge_list: {
    id: string
    name: string
    cover_url: string
    docs: {
      id: string
      name: string
    }[]
  }[]
}

export interface CreateKnowledgeResponse {
  knowledge: KnowledgeBase
}


