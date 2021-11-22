package garages;

import java.io.PrintStream;
import java.util.*;

public class Voiture {

    private final String immatriculation;
    private final List<Stationnement> myStationnements = new LinkedList<>();

    public Voiture(String i) {
        if (null == i) {
            throw new IllegalArgumentException("Une voiture doit avoir une immatriculation");
        }

        immatriculation = i;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    /**
     * Fait rentrer la voiture au garage Précondition : la voiture ne doit pas
     * être déjà dans un garage
     *
     * @param g le garage où la voiture va stationner
     * @throws java.lang.Exception Si déjà dans un garage
     */
    public void entreAuGarage(Garage g) throws Exception {
        //Si la voiture est déjà dans un garage on lève une exception
        if(this.estDansUnGarage()==true) throw new IllegalArgumentException("La voiture est déjà dans un garage");
        
        //Sinon on créé un nouveau stationnement et on l'ajoute dans myStationnements
        else{
            Stationnement s = new Stationnement(this,g);
            myStationnements.add(s);
        }
    }

    /**
     * Fait sortir la voiture du garage Précondition : la voiture doit être dans
     * un garage
     *
     * @throws java.lang.Exception si la voiture n'est pas dans un garage
     */
    public void sortDuGarage() throws Exception {
        //Si la voiture est déjà dans un garage on lève une exception
        if(this.estDansUnGarage()==false) throw new IllegalArgumentException("La voiture n'est pas dans un garage");
        
        // Sinon on termine ce stationnement
        else{
           //On récupère le dernier stationnement de la liste
            Stationnement last = myStationnements.get(myStationnements.size() - 1);
            //On termine
            last.terminer();
        }
    }

    /**
     * @return l'ensemble des garages visités par cette voiture
     */
    public Set<Garage> garagesVisites() {
        Set garageVisites = new HashSet<Garage>();
        
        //On parcourt la liste de stationnements
        for(Stationnement s : myStationnements){
            garageVisites.add(s.getGarage());
        }
        return garageVisites;
    }

    /**
     * @return vrai si la voiture est dans un garage, faux sinon
     */
    public boolean estDansUnGarage() {
        //On test si le dernier stationnement est en cours
        if (myStationnements.size() - 1 == -1) {
            return false;
        }

        //On récupère le dernier stationnement de la liste
        Stationnement last = myStationnements.get(myStationnements.size() - 1);

        // Vrai si le dernier stationnement est en cours
        if (last.estEnCours()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Pour chaque garage visité, imprime le nom de ce garage suivi de la liste
     * des dates d'entrée / sortie dans ce garage
     * <br>Exemple :
     * <pre>
     * Garage Castres:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     *		Stationnement{ entree=28/01/2019, en cours }
     *  Garage Albi:
     *		Stationnement{ entree=28/01/2019, sortie=28/01/2019 }
     * </pre>
     *
     * @param out l'endroit où imprimer (ex: System.out)
     */
    
    //Remarque pour System.out.println()
    //System --> classe
    //Propriété statique (sortie stantard) dans la classe PrintStream
    
    public void imprimeStationnements(PrintStream out) {
        for(Garage g : this.garagesVisites()){
            out.println(g + ":");
            for(Stationnement s : myStationnements){
                if(s.getGarage().equals(g)){
                    out.println("       " + s);
                }
            }
        }
    }

}
