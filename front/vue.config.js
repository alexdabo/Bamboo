
if (process.env.NODE_ENV === 'production') { console.log(`PUBLIC_PATH: ${process.env.PUBLIC_PATH || '/'}`) }
module.exports = {
  outputDir: '../src/main/webapp',
  publicPath: process.env.PUBLIC_PATH || '/'
}
