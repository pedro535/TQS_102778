module.exports = {
  content: [
    './src/**/*.{js,jsx,ts,tsx}',
    'node_modules/flowbite-react/**/*.{js,jsx,ts,tsx}'
  ],
  theme: {
    extend: {
      colors: {
        'dark-blue': '#041c30'
      },
    },
  },
  plugins: [
    require('flowbite/plugin')
  ],
}