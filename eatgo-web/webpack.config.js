const path = require('path');

module.exports = {
    context: __dirname,
    entry: './src/app.js',
    output: {
        path: path.resolve(__dirname, 'js'),
        publicPath: '/js/',
        filename: 'app.js'
    },
    module: {
        rules: [
            {
                test: /\.css$/,
                exclude: /node_modules/,
                use: ['style-loader', 'css-loader']
            },
        ]
    },
    devServer: {
        static: {
            directory: path.resolve(__dirname, 'html'),
        },
        port: 9000
    }
};