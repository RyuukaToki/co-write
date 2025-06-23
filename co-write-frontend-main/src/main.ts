import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import router from './router'
import { useUmoEditor } from '@umoteam/editor';
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import { createPinia } from 'pinia' // 导入 Pinia

const pinia = createPinia()
const app = createApp(App);

app.use(router);
app.use(ElementPlus);
app.use(useUmoEditor);
app.use(pinia)

app.mount('#app');
