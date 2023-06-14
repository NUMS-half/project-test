export const setItem = (key, value) => {
  let valStr = value
  if (typeof value !== 'string') valStr = JSON.stringify(value)
  localStorage.setItem(key, valStr)
}

export const getItem = (key) => {
  try {
    var str = localStorage.getItem(key)
    if(str.startsWith("[") && str.endsWith("]"))
      str = str.slice(1,-1);
    let value = JSON.parse(str)
    return value
  } catch (err) {
    return localStorage.getItem(key)
  }
}

export const clearItem = () => {
  localStorage.clear()
}

export const setPageParam = (key, value) => {
  setItem('pageParams', {
    ...getItem('pageParams'),
    [key]: value
  })
}

export const getPageParam = (key) => {
  let params = getItem('pageParams')
  return params[key]
}
