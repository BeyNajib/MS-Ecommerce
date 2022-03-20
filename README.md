# MS-Ecommerce

Jules / Najib / Julien

# Fonctions réalisées

toutes les fonctions demandées ont été réalisées.

Liste des paniers et des commanders disponibles dans les endpoints pour bien vérifier leurs ajouts.

## Particularités

La navigation du entre la liste de produit et le détail est sans problème, mais pour envoyer une commande, même si c'est le même endpoint (GET) que l'ajout dans le panier (POST), ce qui devrait donc renvoyer vers la page avec le bouton pour envoyer la commande, l'ajout dans le panier utilisera l'id d'un produit (commence par 0), alors que la commande nécessitera l'id du panier (commence par 1). Il faudra donc changer peut-etre changer l'id pour bien envoyer le bon panier.
  
