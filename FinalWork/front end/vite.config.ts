import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue()],
  resolve:{
  },
  // 配置代理
  server: {
    proxy: {
      '/api': { // 获取请求中带 /api 的请求
        target: 'http://localhost:8080',  // 后台服务器的源
        changeOrigin: true,   // 修改源
        rewrite: (path) => path.replace(/^\/api/, "")   //  /api 替换为空字符串
      }
    }
  }
})
