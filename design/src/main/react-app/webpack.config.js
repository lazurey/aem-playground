const path = require('path')
const webpack = require('webpack')
const CopyWebpackPlugin = require('copy-webpack-plugin')

const AEM_DESIGN_PATH = 'resources/jcr_root/etc/designs/react-libs/clientlibs/script'

module.exports = {
  entry: './app.js',
  output: {
    path: path.join(__dirname, './static'),
    filename: 'bundle.js'
  },
  module: {
    loaders: [
      {
        test: /\.html$/,
        loader: 'file?name=[name].[ext]'
      },
      {
        test: /.(js|jsx)?$/,
        loader: 'babel-loader',
        exclude: /node_modules/,
        query: {
          presets: ['es2015', 'react']
        }
      }
    ]
  },
  plugins: [
    new CopyWebpackPlugin([
      {
        from: './static',
        to: path.join(__dirname, `../${AEM_DESIGN_PATH}`),
        copyUnmodified: true
      }
    ])
  ]
};