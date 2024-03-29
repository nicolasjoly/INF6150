/**
 * I N F 1 1 2 0 - TP2 hiver 2009
 *
 * Classe fournie pour le Tp2
 * 
 * ==============================> Placez cette classe dans votre projet
 * 
 * @author Louise Laforest 
 */

import java.util.*;

public class JeuArithmetique {

    public static final int FACILE = 1;
    public static final int MOYEN = 2;
    public static final int DIFFICILE = 3;
    public static final int EXTREME = 4;
    
    
    private static int degreDifficulte = FACILE;
    private static int germe = 25;
    private static Random generateur = new Random ( germe );
    
	/*******************************************
	 * Amelioration potentielle:
	 * private static Random generateur = new Random (System.currentTimeMillis());
	 * 
	 * Ceci permet d'avoir des questions differentes a chaque nouvelle partie
	 *******************************************/
    
    private static final int OP_MIN_FACILE = 1;
    private static final int OP_MAX_FACILE = 20;
    private static final int OP_MIN_MOYEN = 1;
    private static final int OP_MAX_MOYEN = 50;
    private static final int OP_MIN_DIFFICILE = -25;
    private static final int OP_MAX_DIFFICILE = 25;
    
    /**
     * Permet de definir le degre de difficulte.
     * FACILE : operations + et -, operandes entre 1 et 20 inclusivement
     * MOYEN  : operations +, -, *, et /, operandes entre 1 et 50 inclusivement
     * DIFFICILE : operations +, -, *, / et %, operandes entre -25 et 25 inclusivement (sauf 0)
     * EXTREME : Version 1 operations +, -, *, /, % et ^, operandes entre -25 et 25 inclusivement (sauf 0). Si l'operation est ^, le premier chiffre est entre 2 et 10, l'exposant est entre 2 et 3.
     * 			 Version 2 operations +, -, *, / et %, operandes entre -25 et 25 inclusivement (sauf 0)
     * @param degre degre de difficulte.  Les choix sont: FACILE, MOYEN, DIFFICILE et EXTREME.
     */
    public static void choisirDegreDifficulte ( int degre ) {
        if ( degre == MOYEN || degre == DIFFICILE || degre == EXTREME) {
            degreDifficulte = degre;
        } else {
            degreDifficulte = FACILE;
        } 
    } // choisirDegreDifficulte

    /**
     * Retourne une operation choisie au hasard en fonction du degre de difficulte choisi
     * @return le caractere correspondant a l'operation
     */
	/*******************************************
	 * Ameliorations apportees:
	 * Modification du code suivant afin de permettre le cas EXTREME.
	 * Modification de la formule afin d'accomoder le changement fait au debut (MOYEN = 2; a la place de 3)
	 *******************************************/
    public static char operationAuHasard () {
        final String operations = "+-*/%^";
        
        int pos = 0;
        switch(degreDifficulte){
        case FACILE:
        	pos = 1;
        	break;
        case MOYEN:
        	pos = 3;
        	break;
        case DIFFICILE:
        	pos = 4;
        	break;
        case EXTREME:
        	pos = 5;
        	break;
        };

        return operations.charAt ( nombreAleatoire ( 0, pos ) );
    } // operationAuHasard
    
    /**
     * Retourne un nombre choisi au hasard en fonction du degre de difficulte choisi
     * @return le nombre choisi au hasard
     */
    public static int operandeAuHasard () {
        int operande;
        if ( degreDifficulte == FACILE ) {
        	operande = nombreAleatoire ( OP_MIN_FACILE, OP_MAX_FACILE );
        } else if ( degreDifficulte == MOYEN ) {
        	operande = nombreAleatoire ( OP_MIN_MOYEN, OP_MAX_MOYEN );
        } else {
            do {
            	operande = nombreAleatoire ( OP_MIN_DIFFICILE, OP_MAX_DIFFICILE );
            } while ( operande == 0 );
        }
        return operande;
    } // operandeAuHasard

    
    private static int nombreAleatoire ( int min, int max ) {
        int reponse;
        if ( min > max ) {
            reponse = 0;
        } else {
            reponse = (int) Math.floor ( ( max - min + 1 ) * generateur.nextDouble () ) + min;
        }
        return reponse;
    } // nombreAleatoire
    
} // JeuArithmetique
