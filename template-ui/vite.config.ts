import react from '@vitejs/plugin-react'
import path from 'path'
import { defineConfig } from 'vite'

// https://vitejs.dev/config/
export default defineConfig(({ mode, command }) => {
  return {
    server: {
      proxy: {
        "/ai-arith-manager": {
          // target: "http://192.168.62.68:13060",
          target: "https://192.168.62.66:20443",
          changeOrigin: true,
          secure: false,
          // rewrite: (path) => path.replace(/^\/media-sample/, '')
        },
        "/erupt-api": {
          target: "http://localhost:18888",
          changeOrigin: true,
          secure: false,
          // rewrite: (path) => path.replace(/^\/media-sample/, '')
        },
      }
    },
    plugins: [react()],
    resolve: {
      // https://cn.vitejs.dev/config/#resolve-alias
      alias: {
        // 设置路径
        '~': path.resolve(__dirname, './'),
        // 设置别名
        '@': path.resolve(__dirname, './src'),
      },
      // https://cn.vitejs.dev/config/#resolve-extensions
      extensions: ['.mjs', '.js', '.ts', '.jsx', '.tsx', '.json', '.vue']
    },
  }
})
