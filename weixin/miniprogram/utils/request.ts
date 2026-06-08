/**
 * 网络请求封装
 */
const BASE_URL = 'http://192.168.16.146:8080/api'

interface RequestOptions {
  url: string
  method?: 'GET' | 'POST' | 'PUT' | 'DELETE'
  data?: any
  showLoading?: boolean
}

/**
 * 发起HTTP请求
 */
export function request<T = any>(options: RequestOptions): Promise<T> {
  const { url, method = 'GET', data, showLoading = true } = options

  if (showLoading) {
    wx.showLoading({ title: '加载中...', mask: true })
  }

  return new Promise((resolve, reject) => {
    wx.request({
      url: BASE_URL + url,
      method,
      data,
      header: { 'Content-Type': 'application/json' },
      success(res: any) {
        if (showLoading) wx.hideLoading()
        const result = res.data
        if (result.code === 200) {
          resolve(result)
        } else {
          wx.showToast({ title: result.msg || '请求失败', icon: 'none' })
          reject(result)
        }
      },
      fail(err) {
        if (showLoading) wx.hideLoading()
        wx.showToast({ title: '网络错误', icon: 'none' })
        reject(err)
      }
    })
  })
}

/**
 * GET请求
 */
export function get<T = any>(url: string, data?: any): Promise<T> {
  return request<T>({ url, method: 'GET', data })
}

/**
 * POST请求
 */
export function post<T = any>(url: string, data?: any): Promise<T> {
  return request<T>({ url, method: 'POST', data })
}

/**
 * PUT请求
 */
export function put<T = any>(url: string, data?: any): Promise<T> {
  return request<T>({ url, method: 'PUT', data })
}

/**
 * DELETE请求
 */
export function del<T = any>(url: string, data?: any): Promise<T> {
  return request<T>({ url, method: 'DELETE', data })
}
