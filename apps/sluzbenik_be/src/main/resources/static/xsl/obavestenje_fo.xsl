<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format"
	xmlns:xs="http://www.w3.org/2001/XMLSchema" version="2.0">
	<xsl:template match="/">
		<fo:root>
			<fo:layout-master-set>
				<fo:simple-page-master
					master-name="bookstore-page">
					<fo:region-body margin-top="0.75in"
						margin-bottom="0.75in" margin-left="80pt" margin-right="80pt" />
				</fo:simple-page-master>
			</fo:layout-master-set>

			<fo:page-sequence master-reference="bookstore-page">
				<fo:flow flow-name="xsl-region-body">
					<fo:block font-family="Times New Roman" margin-top="30pt"
						font-size="11pt" text-align="left" text-decoration="underline">
						<xsl:value-of
							select="/obavestenje/osnovni_podaci/podaci_o_organu/naziv"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman" text-align="left"
						font-size="11pt" text-decoration="underline">
						<xsl:value-of
							select="/obavestenje/osnovni_podaci/podaci_o_organu/sediste"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman" text-align="left"
						font-size="11pt">
						(назив и седиште органа)
					</fo:block>
					<fo:block font-family="Times New Roman" text-align="left"
						font-size="11pt" text-decoration="underline">
						Број предмета:
						<xsl:value-of select="/obavestenje/osnovni_podaci/podaci_o_organu/broj_predmeta"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman" text-align="left"
						font-size="11pt">
						Датум:
						<fo:inline text-indent="8em" text-decoration="underline">
							<xsl:value-of
								select="/obavestenje/osnovni_podaci/podaci_o_organu/datum_zahteva"></xsl:value-of>
						</fo:inline>
					</fo:block>
					<fo:block font-family="Times New Roman" text-align="left"
						font-size="11pt" margin-top="11pt" text-decoration="underline">
						<xsl:value-of
							select="/obavestenje/osnovni_podaci/podaci_o_podnosiocu/ime_i_prezime"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman" text-align="left"
						font-size="11pt" text-decoration="underline">
						<xsl:value-of
							select="/obavestenje/osnovni_podaci/podaci_o_podnosiocu/naziv"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman" text-align="left"
						font-size="11pt" text-decoration="underline">
						<xsl:value-of
							select="/obavestenje/osnovni_podaci/podaci_o_podnosiocu/adresa"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman" text-align="left"
						font-size="11pt">
						Име и презиме / назив / и адреса подносиоца захтева
					</fo:block>
					<fo:block font-family="Times New Roman"
						text-align="center" font-size="12pt" margin-top="22pt"
						font-weight="bold">
						О Б А В Е Ш Т Е Њ Е
					</fo:block>
					<fo:block font-family="Times New Roman"
						text-align="center" font-size="12pt" margin-top="5pt"
						font-weight="bold">
						о стављању на
						увид документа
					</fo:block>

					<fo:block font-family="Times New Roman"
						text-align="justify" font-size="11pt" margin-top="11pt"
						text-indent="4em">
						На основу члана 16. ст. 1. Закона о слободном приступу
						информацијама
						од јавног значаја, поступајући по вашем захтеву за
						слободан
						приступ информацијама од
						<fo:inline text-decoration="underline" >
							<xsl:value-of
								select="/obavestenje/osnovni_podaci/podaci_o_organu/datum_zahteva"/>
						</fo:inline>
						год., којим сте тражили увид у
						документ/е са информацијама о / у
						вези са:
					</fo:block>
					<fo:block font-family="Times New Roman"
						text-align="center" font-size="11pt" text-decoration="underline" margin-top="20pt">
						<xsl:value-of
							select="/obavestenje/sadrzaj/opis_trazene_informacije"></xsl:value-of>
					</fo:block>
					<fo:block font-family="Times New Roman"
						text-align="center" font-size="11pt">
						(опис тражене информације)
					</fo:block>
					<fo:block font-family="Times New Roman"
						text-align="justify" font-size="11pt" text-indent="4em"
						margin-top="11pt">
						Oбавештавамо вас да дана
						<fo:inline text-decoration="underline">
							<xsl:value-of
								select="/obavestenje/sadrzaj/datum_uvida"></xsl:value-of>
						</fo:inline>
						,
						у
						<fo:inline text-decoration="underline">
							<xsl:value-of
								select="/obavestenje/sadrzaj/cas_uvida"></xsl:value-of>
						</fo:inline>
						
						часова,
						односно у времену
						од
						<fo:inline text-decoration="underline">
							<xsl:value-of
								select="/obavestenje/sadrzaj/sat_od"></xsl:value-of>
						</fo:inline>
						до
						<fo:inline text-decoration="underline">
							<xsl:value-of
								select="/obavestenje/sadrzaj/sat_do"></xsl:value-of>
						</fo:inline>

						часова, у просторијама органа у
						<fo:inline text-decoration="underline">
							<xsl:value-of
								select="/obavestenje/sadrzaj/adresa/grad"></xsl:value-of>
						</fo:inline>
						ул.
						<fo:inline text-decoration="underline">
							<xsl:value-of
								select="/obavestenje/sadrzaj/adresa/ulica"></xsl:value-of>
						</fo:inline>
						бр.
						<fo:inline text-decoration="underline">
							<xsl:value-of
								select="/obavestenje/sadrzaj/adresa/broj"></xsl:value-of>
						</fo:inline>
						,
						канцеларија бр.
						<fo:inline text-decoration="underline">
							<xsl:value-of
								select="/obavestenje/sadrzaj/broj_kancelarije"></xsl:value-of>
						</fo:inline>
						можете
						<fo:inline font-weight="bold">извршити увид</fo:inline>
						у документ/е у коме је садржана тражена
						информација.
					</fo:block>
					<fo:block font-family="Times New Roman"
						text-align="justify" font-size="11pt" text-indent="4em"
						margin-top="11pt">
						Том приликом, на ваш захтев, може вам се издати и копија документа са траженом информацијом.
					</fo:block>
						<fo:block font-family="Times New Roman"
							text-align="justify" font-size="11pt" margin-top="11pt">
							Трошкови су утврђени Уредбом Владе Републике Србије („Сл. гласник
							РС“, бр. 8/06), и то:
							копија стране А4 формата износи 3 динара, А3 формата 6 динара, CD 35 динара, дискете 20 динара, DVD 40 динара, 
							аудио-касета – 150 динара, видео-касета 300 динара, претварање једне стране документа из физичког у електронски облик – 30 динара.
						</fo:block>
						<fo:block font-family="Times New Roman"
							text-align="justify" font-size="11pt" text-indent="4em"
							margin-top="11pt">
							Износ укупних трошкова износи
							<fo:inline text-decoration="underline">
								<xsl:value-of
									select="/obavestenje/sadrzaj/cena"></xsl:value-of>
							</fo:inline>
							динара
							и уплаћује се на жиро-рачун Буџета Републике Србије бр. 
							<fo:inline text-decoration="underline">
								<xsl:value-of
									select="/obavestenje/sadrzaj/ziro_racun"></xsl:value-of>
							</fo:inline>
							, с позивом на број
							<fo:inline text-decoration="underline">
								<xsl:value-of
									select="/obavestenje/sadrzaj/ziro_racun/@poziv_na_broj"></xsl:value-of>
							</fo:inline>
							– ознака шифре општине/града где се налази орган власти
							(из
							Правилника о условима и начину вођења рачуна – „Сл. гласник
							РС“,
							20/07... 40/10).
						</fo:block>
					
					<fo:block font-family="Times New Roman" text-align="left"
						font-size="11pt" margin-top="11pt">
						Достављено:
					</fo:block>
							<fo:block font-family="Times New Roman"
								text-align="left" font-size="11pt">
								1. Именованом
							</fo:block>
							<fo:block font-family="Times New Roman"
								text-align="left" font-size="11pt">
								2. Архиви
							</fo:block>
					<fo:block font-family="Times New Roman"
						text-align="center" font-size="11pt" margin-top="-20pt">
						(М.П.)
					</fo:block>
				</fo:flow>
			</fo:page-sequence>
		</fo:root>
	</xsl:template>
</xsl:stylesheet>