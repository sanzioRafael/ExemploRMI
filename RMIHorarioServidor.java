import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.registry.*;
import java.util.Calendar;
import java.net.*;

public class RMIHorarioServidor extends UnicastRemoteObject implements
		RMIHorarioInterf {

	protected static String nomeObjRemoto; // nome do objeto remoto
	static Registry registro; // registro rmi para localizar os objs remotos.
	static String enderecoIP; // armazena enderco IP desta maquina
	static int porta; // armazena porta a ser ouvida

	public RMIHorarioServidor() throws RemoteException {
		super(); // chama o construtor na classe pai
	}

	@Override
	public String EscreveMensagem() throws RemoteException {
		Calendar calendario = Calendar.getInstance();
		String mens = ("\n==========================\n"
				+ "Ola Pessoal do IFMT " + "\n" + "Horario Local: "
				+ calendario.get(Calendar.HOUR_OF_DAY) + ":"
				+ calendario.get(Calendar.MINUTE) + ":"
				+ calendario.get(Calendar.SECOND) + "\n"
				+ "==========================" + "\n\n");
		System.out.print(mens);
		return mens;
	}

	public static void main(String[] args) {
		// testa obtencao de end IP deste servidor
		try { // obtem endereco IP deste servidor
			enderecoIP = (InetAddress.getLocalHost()).toString();
		} catch (Exception e) {
			System.out.println("Falha na obtencao do endereco IP do servidor!");
		}
		porta = Integer.parseInt(args[0]); // converte argumento entrada
		System.out
				.println("Este endereco IP=" + enderecoIP + ",porta=" + porta);
		nomeObjRemoto = "horario";
		System.out.println("nome obj remoto: " + nomeObjRemoto);
		try { // cria registro (rmiregistry) e associa nome ao objeto remoto
			RMIHorarioServidor stub = new RMIHorarioServidor();
			System.out
					.println("Servidor: Registrando o RMIHorarioServidor como \""
							+ nomeObjRemoto + "\"");
			registro = LocateRegistry.createRegistry(porta);
			registro.rebind(nomeObjRemoto, stub);
			System.out.println("Servidor: Pronto...");
		} catch (Exception e) {
			System.out
					.println("Servidor: Falha ao tentar registar RMIHorarioServidor: "
							+ e);
		}
	}

}
