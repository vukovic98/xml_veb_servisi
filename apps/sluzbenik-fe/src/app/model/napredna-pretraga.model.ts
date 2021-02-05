export interface ZalbaNapredna {
  zahtev: string,
  mejl: string,
  organ: string,
  and: boolean
}

export interface ZahtevNapredna{
  ime: string,
  mail: string,
  organ: string,
  and: boolean
}

export interface ObavestenjeNapredna{
  predmet: string,
  zahtev: string,
  ime: string,
  and: boolean
}

export interface ResenjeNapredna{
  zalba: string,
  ishod: string,
  korisnik: string,
  and: boolean
}
