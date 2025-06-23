interface CommentData {
    id: string;
    content: string;
    selectedText: string;
    documentId: string;
    createdAt?: Date;
  }
  
  async function saveCommentToAPI(comment: CommentData): Promise<void> {
    // 实际API调用逻辑
    return fetch('/api/comments', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(comment)
    }).then(response => {
      if (!response.ok) throw new Error('评论提交失败');
    });
  }