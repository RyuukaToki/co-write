<template>
    <div class="editor-container">
        <div class="toolbar">
            <button @click="handleSummary" :disabled="loading">
                {{ loading ? '生成中...' : '生成AI摘要' }}
            </button>
        </div>
        <umo-editor
          ref="editorRef"
          v-bind="options"
        >
        <template #toolbar_base>
            <umo-menu-button
                :ico="commentIcon"
                text="添加评论"
                :huge="true"
                @menu-click="handleAddComment"
            />
        </template>
        </umo-editor>

    
        <div v-if="summaryVisible" class="summary-sidebar">
            <h3>AI摘要</h3>
            <div class="summary-content" v-html="summaryHtml"></div>
            <button @click="summaryVisible = false" class="close-btn">关闭</button>
        </div> 
        
        <CommentSidebar
            v-if="showCommentSidebar"
            :selectedText="selectedCommentText"
            @submit="handleCommentSubmit"
            @close="showCommentSidebar = false"
        />
        
    </div>
</template>

<script setup lang="ts">
import { reactive, ref, computed } from 'vue';
import { UmoEditor } from '@umoteam/editor';
import { marked } from 'marked';
import CommentSystem from "./CommentSystem.vue"
import { UmoMenuButton } from '@umoteam/editor'
import Highlight from '@tiptap/extension-highlight'
import CommentSidebar from '@/components/editor/CommentSidebar.vue' 


// UmoEditor 配置
const options = reactive({});

const editorRef = ref();
const tiptapEditor = ref({});
const summary = ref('');
const summaryVisible = ref(false);
const showCommentSidebar = ref(false);
const loading = ref(false);
const selectedCommentText = ref('')
const summaryHtml = computed(() => marked.parse(summary.value || ''));

const commentIcon = `
<svg width="24" height="24" viewBox="0 0 24 24" fill="#1890ff" xmlns="http://www.w3.org/2000/svg">
  <path d="M21 15a2 2 0 0 1-2 2H7l-4 4V5a2 2 0 0 1 2-2h14a2 2 0 0 1 2 2v10z"/>
</svg>
`
function handleAddComment (){
    const editor = editorRef.value;
    let selectedText = '';
    
    if (editor && editor.getSelectionText) {
        selectedText = editor.getSelectionText();
    } else {
        // 兜底：用window.getSelection
        selectedText = window.getSelection()?.toString() || '';
    }
    if (!selectedText) {
        alert('请先选中需要评论的内容');
        return;
    }

    // 1. 保存选中文本并打开侧边栏
    selectedCommentText.value = selectedText;
    showCommentSidebar.value = true;

    // 2.高亮选中文本
    console.log(editor.getSelectionText.commands);
    
    // 3. 打印调试信息
    console.log('准备评论内容:', selectedText);

}

// 处理评论提交
const handleCommentSubmit = (content: string) => {
  if (!selectedCommentText.value) return;

  // 调用API保存评论
  saveCommentToAPI({
    content,
    selectedText: selectedCommentText.value,
    documentId: currentDocumentId.value // 假设有这个变量
  }).then(() => {
    // 保存成功后关闭侧边栏
    // showCommentSidebar.value = false;
    commentContent.value = '';
    
    // 更新高亮状态
    if (editorRef.value?.commands?.updateComment) {
      editorRef.value.commands.updateComment({
        status: 'submitted'
      });
    }
  });
};

async function handleSummary() {
    // 获取选中的文本
    const editor = editorRef.value;
    let selectedText = '';
    if (editor && editor.getSelectionText) {
        selectedText = editor.getSelectionText();
    } else {
        // 兜底：用window.getSelection
        selectedText = window.getSelection()?.toString() || '';
    }
    if (!selectedText) {
        alert('请先选中需要生成摘要的内容');
        return;
    }
    loading.value = true;
    summaryVisible.value = true;
    summary.value = '正在生成摘要...';
    try {
        const res = await fetch('https://one-api-other.nowcoder.com/v1/chat/completions', {
            method: 'POST',
            headers: {
                'Authorization': 'sk-iLG1IoQfktTmn1Wm723e91DbF54b4d61970eBa26654eD184',
                'Content-Type': 'application/json',
                'Accept': 'application/json'
            },
            body: JSON.stringify({
                model: 'gpt-4o-mini',
                messages: [
                    { role: 'system', content: '你是一个文档agent,根据文档内容生成智能摘要，摘要字数在100字左右' },
                    { role: 'user', content: selectedText }
                ],
                temperature: 0.7
            })
        });
        const data = await res.json();
        summary.value = data.choices?.[0]?.message?.content?.trim() || '生成失败';
    } catch (e) {
        summary.value = '很抱歉，当前系统繁忙，请稍后再试。';
    }
    loading.value = false;
}
</script>

<style scoped>
.editor-container {
    width: 100%;
    height: 100%;
    box-sizing: border-box;
    position: relative;
    display: flex;
    flex-direction: row;
}
.toolbar {
    position: absolute;
    top: 300px; /* 下移，避免遮挡编辑器 */
    left: 100px;
    z-index: 10;
    background: #a0c5fa;
}

.toolbar button {
    background: #1677ff;
    color: #fff;
    border: none;
    padding: 8px 20px;
    border-radius: 4px;
    font-weight: bold;
    cursor: pointer;
    transition: background 0.2s;
}
.summary-sidebar {
    position: fixed;
    top: 250px;
    right: 0;
    width: 320px;
    height: 40vh;
    background: #fafbfc;
    border-left: 1px solid #eee;
    padding: 16px 16px 0 16px;
    z-index: 9999;
    box-shadow: -2px 0 8px #eee;
    border-radius: 8px 0 0 8px;
    display: flex;
    flex-direction: column;
}
.summary-content {
    flex: 1 1 0;
    overflow-y: auto;
    white-space: pre-wrap;
    margin-bottom: 16px;
}
.close-btn {
    margin: 16px 0 16px 0;
    background: #ff4d4f;
    color: #fff;
    border: none;
    padding: 8px 32px;
    border-radius: 4px;
    cursor: pointer;
    font-weight: bold;
    transition: background 0.2s;
    align-self: flex-start;
}
.close-btn:hover {
    background: #d9363e;
}


</style>
