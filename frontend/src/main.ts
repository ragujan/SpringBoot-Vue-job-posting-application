
import { createApp } from 'vue'
import App from './App.vue'
import { createMemoryHistory, createRouter } from 'vue-router'
import router from './router'
createApp(App).use(router).mount('#app')
