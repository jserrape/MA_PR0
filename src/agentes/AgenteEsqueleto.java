/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package agentes;

import GUI.AgenteDemoJFrame;
import jade.core.Agent;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.domain.FIPAException;

/**
 *
 * Esqueleto de agente para la estructura general que deben tener todos los
 * agentes
 */
public class AgenteEsqueleto extends Agent {

    //Variables del agente
    private AgenteDemoJFrame myGui;

    @Override
    protected void setup() {
        
        //Inicialización de las variables del agente

        //Configuración del GUI
        myGui = new AgenteDemoJFrame(this);
        myGui.setVisible(true);

        //Registro del agente en las Páginas Amarrillas
        myGui.presentarSalida("Agente registrado en las paginas amarillas\n");
        DFAgentDescription dfd = new DFAgentDescription();
        dfd.setName(getAID());
        ServiceDescription sd = new ServiceDescription();
        sd.setType("Tipo de Servicio");
        sd.setName("Nombre del Servicio");
        dfd.addServices(sd);
        try {
            DFService.register(this, dfd);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        //Registro de la Ontología
        
        
        System.out.println("Se inicia la ejecución del agente: " + this.getName());

        
        //Añadir las tareas principales
    }

    @Override
    protected void takeDown() {
        //Desregristo del agente de las Páginas Amarillas
        try {
            DFService.deregister(this);
        } catch (FIPAException fe) {
            fe.printStackTrace();
        }

        //Liberación de recursos, incluido el GUI
        myGui.dispose();

        //Despedida
        System.out.println("Finaliza la ejecución del agente: " + this.getName());
    }

    //Métodos de trabajo del agente
    //Clases internas que representan las tareas del agente
}
