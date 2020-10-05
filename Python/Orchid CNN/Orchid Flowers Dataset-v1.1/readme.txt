Orchid Flower Dataset
-----------------------------------
This dataset has 7156 images of orchid flowers, consist of 156 orchid species.
Most of the images are Flickr images under the Creative Commons license and some of the images are obtained from websites like Go Botany (Native Plant Trust), Encyclopedia of Life (EoL), etc.
All of the credits have been declared on the credit.txt.
	
If you use "Orchid Flower Dataset" in your work, please cite this dataset:

	Apriyanti, D.H.; Spreeuwers, L.J.; Lucas, P.J.F.; Veldhuis, R.N.J., 2020, "Orchid Flowers Dataset", https://doi.org/10.7910/DVN/0HNECY, Harvard Dataverse, V1.

Different from other flower datasets, besides containing images, this dataset also contains flower's characteristics.
Flower's characteristics included in this dataset are Texture, Labellum Characteristics, Inflorescence, Number of Flower, Color of Flower, and Color of Labellum.
The characteristics were extracted based on the descriptions from "Go Botany" website.

The images can be found in folder 'Orchid_images' and flower's characteristics can be found in some text files for feature classifiers purposes.
Besides used for multi-class classification, this dataset also supports multi-label classification.
We divided the dataset into three parts for training, validation, and testing. The percentage of each part is about 70:20:10.

The symbols used in each text file are explained below:
-----------------------------------
Texture of the Labellum:
1: Nospot
2: Spot

Labellum Characteristics:
1: Fringed
2: Lobed
3: Pouched
4: Simple

Inflorescence:
1: Panicle
2: Raceme
3: SingleOrPair
4: Spike

Number of Flower:
1. A Few
2. Many
3. SinglePair

Color:
	- Version 1:
		- Color of Flower (CF):
			1. Brown
			2. Green
			3. GreenBrown
			4. GreenWhite
			5. GreenYellow
			6. Orange
			7. Pink
			8. PinkWhite
			9. Purple
			10. PurpleWhite
			11. Red
			12. White
			13. Yellow
			14. YellowBrown
			15. YellowPurple

		- Color of Labellum (CL):
			1: BluePurple
			2: Green
			3: Orange
			4: PinkRed
			5: PinkRedWhite
			6: PurpleYellow
			7: White
			8: WhiteYellow
			9: Yellow
			10: YellowBrown

	- Version 2:
		- Color of Flower (CF):
			1. Combining Red and Orange, Combining White and Yellow (CF1):
				1	Green
				2	GreenRed
				3	GreenYellow
				4	Purple
				5	PurpleYellow
				6	Red
				7	RedYellow
				8	Yellow
			2. Combining Orange and Yellow, Keep Red and White Separate (CF2):
				1	Green
				2	GreenRed
				3	GreenWhite
				4	GreenYellow
				5	Purple
				6	PurpleWhite
				7	Red
				8	RedYellow
				9	White
				10	Yellow
				11	YellowPurple


		- Color of Labellum (CL):
			There are 2 scenarios:
			1. Combining Red and Orange, Combining White and Yellow (CL1):
				1	Green
				2	GreenRed
				3	GreenYellow
				4	Purple
				5	PurpleYellow
				6	Red
				7	RedYellow
				8	Yellow
			2. Combining Orange and Yellow, Keep Red and White Separate (CL2)
				1	Green
				2	GreenRed
				3	GreenWhite
				4	Purple
				5	PurpleWhite
				6	PurpleYellow
				7	RedYellow
				8	White
				9	WhiteYellow
				10	Yellow


Species:
1	Amerorchis rotundifolia
2	Aplectrum hyemale
3	Arethusa bulbosa
4	Bletia patula
5	Bletia purpurea
6	Brassia caudata 
7	Calopogon barbatus
8	Calopogon multiflorus
9	Calopogon oklahomensis
10	Calopogon pallidus
11	Calopogon tuberosus
12	Calypso bulbosa
13	Cephalanthera austiniae
14	Cleistesiopsis bifaria
15	Cleistesiopsis divaricata
16	Cleistesiopsis oricamporum
17	Coeloglossum viride
18	Corallorhiza bentleyi
19	Corallorhiza maculata
20	Corallorhiza mertensiana
21	Corallorhiza odontorhiza
22	Corallorhiza striata
23	Corallorhiza trifida
24	Corallorhiza wisteriana
25	Cranichis muscosa
26	Cyclopogon elatus
27	Cypripedium acaule
28	Cypripedium arietinum
29	Cypripedium californicum
30	Cypripedium candidum
31	Cypripedium fasciculatum
32	Cypripedium montanum
33	Cypripedium parviflorum
34	Cypripedium passerinum
35	Cypripedium reginae
36	Cypripedium yatabeanum
37	Cypripedium guttatum
38	Cyrtopodium flavum
39	Cyrtopodium punctatum
40	Dactylorhiza aristata 
41	Dactylorhiza viridis
42	Dendrophylax lindenii
43	Dendrophylax porrectus 
44	Eltroplectris calcarata
45	Encyclia tampensis
46	Epidendrum amphistomum
47	Epidendrum magnoliae
48	Epidendrum nocturnum
49	Epidendrum rigidum
50	Epipactis atrorubens
51	Epipactis gigantea
52	Epipactis helleborine
53	Epipactis palustris
54	Eulophia alta
55	Eulophia ecristata
56	Eulophia graminea
57	Galeandra bicarinata 
58	Galearis rotundifolia
59	Galearis spectabilis
60	Goodyera oblongifolia
61	Goodyera pubescens
62	Goodyera repens
63	Goodyera tesselata
64	Gymnadenia conopsea
65	Habenaria floribunda
66	Habenaria macroceratitis
67	Habenaria quinqueseta
68	Habenaria repens
69	Hexalectris grandiflora
70	Hexalectris nitida
71	Hexalectris revoluta
72	Hexalectris spicata 
73	Ionopsis utricularioides
74	Isotria medeoloides
75	Isotria verticillata
76	Liparis hawaiensis
77	Liparis loeselii
78	Liparis nervosa
79	Malaxis abieticola 
80	Malaxis macrostachya
81	Malaxis monophyllos
82	Malaxis porphyrea 
83	Malaxis spicata
84	Malaxis unifolia
85	Neottia auriculata
86	Neottia banksiana
87	Neottia borealis
88	Neottia convallarioides
89	Oeceoclades maculata
90	Oncidium ensatum
91	Phaius tankervilleae
92	Platanthera aquilonis
93	Platanthera blephariglottis
94	Platanthera brevifolia
95	Platanthera chapmanii
96	Platanthera ciliaris
97	Platanthera clavellata
98	Platanthera cooperi
99	Platanthera cristata
100	Platanthera dilatata
101	Platanthera elegans 
102	Platanthera ephemerantha
103	Platanthera flava
104	Platanthera grandiflora
105	Platanthera hookeri
106	Platanthera huronensis
107	Platanthera hyperborea
108	Platanthera integra 
109	Platanthera integrilabia
110	Platanthera lacera
111	Platanthera leucophaea
112	Platanthera nivea
113	Platanthera obtusata
114	Platanthera orbiculata
115	Platanthera peramoena
116	Platanthera praeclara
117	Platanthera psycodes
118	Platanthera purpurascens
119	Platanthera sparsiflora
120	Platanthera stricta
121	Platanthera tescamnis
122	Platanthera transversa
123	Platanthera unalascensis 
124	Platanthera yosemitensis
125	Pogonia ophioglossoides
126	Polystachya concreta
127	Ponthieva racemosa
128	Prosthechea cochleata 
129	Pseudorchis albida
130	Sacoila lanceolata
131	Schiedeella arizonica
132	Spathoglottis plicata
133	Spiranthes cernua
134	Spiranthes diluvialis
135	Spiranthes lacera
136	Spiranthes laciniata 
137	Spiranthes longilabris
138	Spiranthes lucida
139	Spiranthes magnicamporum
140	Spiranthes ochroleuca
141	Spiranthes odorata
142	Spiranthes ovalis
143	Spiranthes porrifolia 
144	Spiranthes praecox
145	Spiranthes romanzoffiana
146	Spiranthes stellata 
147	Spiranthes torta
148	Spiranthes triloba
149	Spiranthes tuberosa
150	Spiranthes vernalis
151	Tipularia discolor
152	Trichocentrum undulatum 
153	Triphora trianthophora
154	Vanilla barbellata
155	Vanilla planifolia
156	Zeuxine strateumatica 