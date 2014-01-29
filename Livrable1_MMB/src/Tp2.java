import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Tp2 {

	public static int saisirDegreDifficulte(){
		//String level = "";// degre de difficulte
		int niveauDifficulte;
		boolean boucleChoixNiveau = true;
		
		do{
			System.out.print("Choisissez un degre de difficulte\n 1: facile\n 2: moyen\n 3: difficile\n 4: extreme\n");// saisir le degre de difficulte
		    niveauDifficulte = Clavier.lireInt(); // valider le degre de difficulte
		    
		    if(niveauDifficulte == 1 || niveauDifficulte == 2  || niveauDifficulte == 3 || niveauDifficulte == 4){
			   boucleChoixNiveau = false;
			   String affichageNiveau = "";
			   
			   switch(niveauDifficulte){
			   case 1:
				   affichageNiveau = "Facile";
				   break;
			   case 2:
				   affichageNiveau = "Moyen";
				   break;
			   case 3:
				   affichageNiveau = "Difficile";
				   break;
			   case 4:
				   affichageNiveau = "Extreme";
				   break;	
			   }
			   
			   System.out.println("Niveau choisi: " + affichageNiveau + "\n");
		   }
		   else{
			   System.out.println("*** choix invalide");// si le choix est invalide
		   }
		
		} while(boucleChoixNiveau);
	    
	    return niveauDifficulte;
	}
	    

	
	public static void main (String[] params) throws ScriptException{
		String nomUtilisateur = "";    //nom de l'utilisateur 
		int difficulte; // la difficulte de l'operation   
		int operande1;
		int operande2;
		int operande3;
		int correction = 0;// les 2 nombres necessaires pour faire les calculs et le resultat obtenu et la correction si l'utilisateur echoue apres 3 tentatives
		String resultat = "";
		String equation = "";
		boolean boucleUneQuestion = true;
		boolean boucleJeu = true;
		int i = 0;
		int j = 0; //compteur
		float score = 0;
		char operation1 = ' ';
		char operation2 = ' ';
		String pourcentage = "";
		
		System.out.println("Ce programme demande a l'utilisateur de saisir son nom, choisir le degre de difficulte, et continue de proposer des operations arithmetiques tant que l'utilisateur n'entre pas fin.");
		System.out.println("Il lui donne le resultat obtenu en fonction des operations faites et du nombre de tentatives faites avant de trouver le bon resultat(3 tentatives max)");
		System.out.println("********************************************\n");
		System.out.println("Veuillez entrer votre nom : ");
		
		nomUtilisateur = Clavier.lireString();
		System.out.println("Bienvenue "+nomUtilisateur+" !");
		difficulte = saisirDegreDifficulte();

		
		
		do{
			i=0;
			boucleUneQuestion = true;
			j++;  
			
			JeuArithmetique.choisirDegreDifficulte(difficulte);
			operande1 = JeuArithmetique.operandeAuHasard ();
			operande2 = JeuArithmetique.operandeAuHasard ();
			operande3 = JeuArithmetique.operandeAuHasard ();
			operation1 = JeuArithmetique.operationAuHasard ();
			System.out.println("Inscrire 'fin' dans la reponse d'une equation pour terminer et obtenir votre score.");
			
			
			/////////////////////PLAGIAT/////////////////////////http://stackoverflow.com/questions/13662001/java-string-to-math-equation
			ScriptEngineManager mgr = new ScriptEngineManager();
			ScriptEngine engine = mgr.getEngineByName("JavaScript");
			
			if(difficulte == JeuArithmetique.EXTREME){
				int nbOp = (int)(Math.random() * 2) + 1;
				if(nbOp == 2){
					JeuArithmetique.choisirDegreDifficulte ( JeuArithmetique.DIFFICILE);
					operation1 = JeuArithmetique.operationAuHasard ();
					operation2 = JeuArithmetique.operationAuHasard ();
					JeuArithmetique.choisirDegreDifficulte ( JeuArithmetique.EXTREME);
					System.out.println(operande1+" "+operation1+" "+operande2+" "+operation2+" "+operande3+" = ?\n" );
					equation = Integer.toString(operande1)+operation1+Integer.toString(operande2)+operation2+Integer.toString(operande3);
				}
				else{
					if(operation1 == '^'){
						operande1 = (int)(Math.random() * 9) + 2;
						operande2 = (int)(Math.random() * 2) + 2;
					}
					System.out.println(operande1+" "+operation1+" "+operande2+" = ?\n" );
					equation = Integer.toString(operande1)+operation1+Integer.toString(operande2);
					
					
				}
			}else{
				System.out.println(operande1+" "+operation1+" "+operande2+" = ?\n" );
				equation = Integer.toString(operande1)+operation1+Integer.toString(operande2);
			}
			
			
			equation = equation.replaceAll("--", "+");
			
			if(operation1 == '^'){
				correction = (int) Math.pow(operande1, operande2);
			}else{
				double correctionTemp = (double) (engine.eval(equation));
				correction = (int)(correctionTemp);
			}
			//////////////////////////////CHEAT MODE///////////////////////////////////////////
			System.out.println(correction);
			
			do{
				i++;
				boolean entreeValide = false;
				
				while(!entreeValide){
					try{
						System.out.print("Entrez votre reponse : ");  
						resultat = Clavier.lireString();
						Integer.parseInt(resultat);
						entreeValide = true;
					}catch(NumberFormatException e){						
						if(resultat.toLowerCase().equals("fin")){
							entreeValide = true;
							boucleJeu = false;
							boucleUneQuestion = false;
							j--;
						}
						else
							System.out.println("Entree invalide");
					}
				}
				
				if(boucleUneQuestion && boucleJeu){
					if(Integer.parseInt(resultat) == correction){
						System.out.println("Bravo "+nomUtilisateur+" ! Bonne reponse !");
						boucleUneQuestion=false;
					}
					else{
						System.out.println("Desole "+nomUtilisateur+", ce n'est pas la bonne reponse.");
						///////////////////////////DEVRAIT PAS AVOIR i++ ICI VU QUE C UN ECHEC SUR LES 3.........SPA LOGIQUE SINON/////////////////////
					}
					
					if(i==3 && boucleUneQuestion){
						System.out.println(nomUtilisateur+", la reponse est : "+correction);
						boucleUneQuestion = false;
						i++;
					}
				}
			} while(boucleUneQuestion);//Sort de la boucle si le joueur trouve la réponse en moins de trois tentatives
			      
			if(boucleJeu)
				score = score + 4 - i;
			    
		}while(boucleJeu);
		
		//le programme donnera la mention appropriee en fonction du score obtenu
		score = (score/3)/j;
		if ( score < 0.45 ) {
			pourcentage = "mediocre";
		}else if ( ( score >= 0.45) && ( score < 0.60) ){
			pourcentage = "passable";
		}else if ( ( score >= 0.60) && ( score < 0.75) ){
			pourcentage = "bien";
		}else if ( ( score >= 0.75) && ( score < 0.90) ){
			pourcentage = "tres bien";
		}else if ( score >= 0.90) {
			pourcentage = "excellent";
		}
		
		System.out.println("Vous avez obtenu "+((int)Math.ceil(score*100))+"%"+" avec la mention: "+pourcentage);
		boucleJeu = false;
	    } // main
} // Tp2
