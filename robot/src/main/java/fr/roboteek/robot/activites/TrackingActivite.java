package fr.roboteek.robot.activites;

import fr.roboteek.robot.decisionnel.Contexte;
import fr.roboteek.robot.memoire.ReconnaissanceFaciale;
import fr.roboteek.robot.systemenerveux.event.MouvementCouEvent;
import fr.roboteek.robot.systemenerveux.event.MouvementCouEvent.MOUVEMENTS_GAUCHE_DROITE;
import fr.roboteek.robot.systemenerveux.event.MouvementCouEvent.MOUVEMENTS_HAUT_BAS;
import fr.roboteek.robot.systemenerveux.event.ReconnaissanceVocaleEvent;
import fr.roboteek.robot.systemenerveux.event.RobotEventBus;
import fr.roboteek.robot.systemenerveux.event.VisagesEvent;

/**
 * Activité "Tracking de visage".
 * @author Nicolas Peltier (nico.peltier@gmail.com)
 */
public class TrackingActivite extends AbstractActivite {

    /**
     * Constructeur
     * @param systemeNerveux système nerveux du robot
     */
    public TrackingActivite(Contexte contexte, ReconnaissanceFaciale reconnaissanceFaciale) {
        super(contexte, reconnaissanceFaciale);
    }
    
    @Override
    public void initialiser() {
        dire("Début du suivi du visage.");
    }

    @Override
    public void handleVisagesEvent(VisagesEvent visagesEvent) {
        suivreVisage(visagesEvent);
    }

    @Override
    public void handleReconnaissanceVocalEvent(ReconnaissanceVocaleEvent reconnaissanceVocaleEvent) {
        
    }

    @Override
    public void arreter() {
        // Arrêt de la tête
        final MouvementCouEvent mouvementTeteEvent = new MouvementCouEvent();
        mouvementTeteEvent.setMouvementGaucheDroite(MOUVEMENTS_GAUCHE_DROITE.STOPPER);
        mouvementTeteEvent.setMouvementHauBas(MOUVEMENTS_HAUT_BAS.STOPPER);
        RobotEventBus.getInstance().publishAsync(mouvementTeteEvent);
        
        dire("Fin du suivi du visage.");
    }

}
