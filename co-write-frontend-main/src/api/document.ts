// // 文档相关 API 接口
// import request from './request'
// import type {
//   CreateDocumentRequest,
//   UpdateDocumentRequest,
//   DeleteDocumentRequest,
//   GetRecentDocumentsRequest,
//   CreateDocumentResponse,
//   UpdateDocumentResponse,
//   DeleteDocumentResponse,
//   GetRecentDocumentsResponse
// } from '@/types/index'


// // 新建文档
// export const createDocument = (data: CreateDocumentRequest): Promise<CreateDocumentResponse> => {
//   return request.post('/api/documents', data)
// }

// // 编辑文档
// export const updateDocument = (id: string, data: UpdateDocumentRequest): Promise<UpdateDocumentResponse> => {
//   return request.put(`/api/documents/${id}`, data)
// }

// // 获取文档详情
// export const getDocumentDetail = (id: string) => {
//   return request.get(`/document/${id}`)
// }

// // 删除文档
// export const deleteDocument = (id: string): Promise<DeleteDocumentResponse> => {
//   return request.delete(`/api/documents/${id}`)
// }


// // 获取最近访问文档
// export const getRecentDocuments = (limit?: number): Promise<GetRecentDocumentsApi['response']> => {
//   return request.get('/api/documents/recent', { params: { limit } })
// } 