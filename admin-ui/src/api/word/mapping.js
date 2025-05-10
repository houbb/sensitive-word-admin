import request from '@/utils/request'

// 查询标签单词映射列表
export function listMapping(query) {
  return request({
    url: '/word/mapping/list',
    method: 'get',
    params: query
  })
}

// 查询标签单词映射详细
export function getMapping(id) {
  return request({
    url: '/word/mapping/' + id,
    method: 'get'
  })
}

// 新增标签单词映射
export function addMapping(data) {
  return request({
    url: '/word/mapping',
    method: 'post',
    data: data
  })
}

// 修改标签单词映射
export function updateMapping(data) {
  return request({
    url: '/word/mapping',
    method: 'put',
    data: data
  })
}

// 删除标签单词映射
export function delMapping(id) {
  return request({
    url: '/word/mapping/' + id,
    method: 'delete'
  })
}
