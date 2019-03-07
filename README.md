# 3i003-Algorithmique
Mini-projet dans le cadre de l'UE Algorithmique



Introduction :

Sur un forum de cuisine, on pourrait lire la question suivante :
“Je viens de cuisiner une grande quantité de confiture et j’ai des bocaux de
tailles diverses prêts pour la verser dedans. Mais je me pose la question sui-
vante : combien de pots, tous bien remplis, me faut-il au minimum pour y
préserver ma confiture ?”
Afin de répondre à cette question d’un point de vue combinatoire, nous allons tenter de
résoudre ce problème de manière concrète. Lors de la mise en bocal, la confiture est encore
liquide et il faut rapidement la verser dans des bocaux comme ceux de la photographie ci-
dessous. Il est important de remplir chaque bocal complètement, c’est-à-dire exactement à
sa capacité : dans le cas contraire, l’air contenu dans le bocal limite le temps de conservation
de la confiture. Mais il existe des bocaux de capacités bien différentes ! On veut proposer
des algorithmes qui s’adaptent aux différentes capacités : on se fixe pour objectif d’utiliser
le moins possible de bocaux.

• Définition du problème

On dispose de S décigrammes (notation dg) de confiture que l’on doit verser dans des
bocaux vides.
On dispose de bocaux de plusieurs capacités que l’on classe en k types de bocaux
différents : chaque type de bocal correspondant à une capacité distincte. On appellera
système de capacités l’ensemble des capacités dont on dipose. On note ces k capacités par
un tableau V de taille k numéroté par ordre croissant, i.e.
V [1] < V [2] < · · · < V [k]
Une capacité V [i] est exprimée par la quantité en décigrammes que l’on peut mettre dans
un bocal. Par exemple, on peut avoir k = 8, avec V = [1, 2, 5, 10, 20, 50, 100, 200].
Pour notre problème, on fait les hypothèses suivantes :
- la quantité S de confiture est un nombre entier de décigrammes, par exemple S =
200 décigrammes (2 kg) ;
- le plus petit des bocaux est de capacité 1 décigramme, i.e. V [1] = 1 ;
- on dispose d’une très grande quantité (supposée ainsi illimitée) de bocaux de cha-
cune des capacités.

Remarquez que pour respecter la première hypothèse, il suffit d’un peu de gourman-
dise. Ces trois hypothèses permettent de dire qu’il y a bien toujours une solution à notre
problème : en effet, il y a toujours une solution en remplissant uniquement des petits pots
de 1 décigramme (10g).
Notre objectif est de remplir le moins possible de bocaux sachant que chaque bocal
doit être rempli exactement à sa capacité maximale. Ainsi, étant donnés :
- un système de k capacités V [i] ∈ N, i ∈ {1, . . . , k} avec V [1] = 1,
- et une quantité totale S ∈ N de confiture,
le but est de déterminer le nombre minimum de bocaux tels que la somme de leurs capacités
est exactement égale à S. On cherche à retourner un couple (n, A) où n est le nombre de
bocaux utilisés et A est un tableau de taille k tel que A[i] P représente le nombre de bocaux
de capacité V [i] à remplir “à fond”. Notez qu’ainsi n = ki=1 A[i].
Pour le tableau de capacité V = [1, 2, 5, 10, 20, 50, 100, 200] et une quantité S =748
décigrammes de confiture par exemple, il faudra remplir au minimum 9 bocaux : 3 de
200dg, 1 de 100dg, 2 de 20dg, 1 de 5dg, 1 de 2dg et 1 de 1dg. Le résultat rendu par un
algorithme résolvant ce problème sera donc le couple (9, 1 1 1 0 2 0 1 3 ).

• Objectifs du projet
Ce projet vise à l’analyse et la mise en œuvre de plusieurs algorithmes résolvant le
problème décrit ci-dessus. Le travail se divise en une partie théorique et une partie
expérimentale. La partie théorique formalise le problème et permet d’établir et d’analy-
ser plusieurs algorithmes ainsi que leurs complexités respectives. La partie expérimentale
porte sur la mise en œuvre de ces algorithmes et la vérification expérimentale de leurs
complexités respectives.


