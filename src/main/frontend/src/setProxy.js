// 서버 포트 와 통신
const { createProxyMiddleware } = require('http-proxy-middleware');

module.exports = function(app) {
  app.use(
    '/api',
    createProxyMiddleware({
      target: 'http://localhost:8081',  // 서버 도메인
      changeOrigin: true,
    })
  );
};