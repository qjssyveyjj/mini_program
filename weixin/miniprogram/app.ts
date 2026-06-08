/**
 * 小程序全局入口
 */
App<IAppOption>({
  globalData: {
    userInfo: null as any,
    baseUrl: 'http://localhost:8080/api'
  },
  onLaunch() {
    const user = wx.getStorageSync('user')
    if (user) {
      this.globalData.userInfo = user
    }
  }
})
