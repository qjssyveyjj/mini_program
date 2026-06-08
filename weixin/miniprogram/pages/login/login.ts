import { post } from '../../utils/request'

/**
 * 登录页面
 */
Page({
  data: {
    username: 'zhangsan',
    password: '123456',
    loading: false
  },

  onShow() {
    const user = wx.getStorageSync('user')
    if (user) {
      wx.switchTab({ url: '/pages/index/index' })
    }
  },

  onUsernameInput(e: any) {
    this.setData({ username: e.detail.value })
  },

  onPasswordInput(e: any) {
    this.setData({ password: e.detail.value })
  },

  /** 用户登录 */
  async handleLogin() {
    const { username, password } = this.data
    if (!username || !password) {
      wx.showToast({ title: '请输入用户名和密码', icon: 'none' })
      return
    }
    this.setData({ loading: true })
    try {
      const res: any = await post('/user/login', { username, password })
      wx.setStorageSync('user', res.data)
      const app = getApp<IAppOption>()
      app.globalData.userInfo = res.data
      wx.showToast({ title: '登录成功', icon: 'success' })
      setTimeout(() => wx.switchTab({ url: '/pages/index/index' }), 500)
    } catch (e) {
      // 错误已在request中处理
    } finally {
      this.setData({ loading: false })
    }
  },

  /** 跳转注册 */
  handleRegister() {
    wx.showModal({
      title: '快速注册',
      editable: true,
      placeholderText: '请输入用户名',
      success: async (res) => {
        if (res.confirm && res.content) {
          try {
            const result: any = await post('/user/register', { username: res.content, nickname: res.content })
            wx.setStorageSync('user', result.data)
            getApp<IAppOption>().globalData.userInfo = result.data
            wx.showToast({ title: '注册成功', icon: 'success' })
            setTimeout(() => wx.switchTab({ url: '/pages/index/index' }), 500)
          } catch (e) { /* handled */ }
        }
      }
    })
  }
})
