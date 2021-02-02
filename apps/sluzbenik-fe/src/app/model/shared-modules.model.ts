export interface ArrayList {
  ArrayList: Array<ItemModel>
}

export interface ItemModel{
  item: Array<any>
}

export interface ObavestenjeMail{
  naslov: string,
  content: string,
  to: string,
  pdf: any,
  html: any
}

export interface OdbijenZahtevMail{
  to: string,
  naslov: string,
  sadrzaj: string
}
