package exemplo;

import java.rmi.*;
import java.rmi.registry.*;

public class RMICliente {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println(args.length);
			System.out.println("Uso: java RMIClient [endereco IP do servidor remoto] [porta]");
			System.exit(1);
		}

		String enderecoIP = args[0];
		int porta = Integer.parseInt(args[1]);
		String nomeObjRemoto = "horario";
		System.out.println("Cliente: Procurando o metodo remoto:" + enderecoIP
				+ "...");

		RMIHorarioInterf stub = null;
		try { // obtem o registro
			Registry registro = LocateRegistry.getRegistry(enderecoIP, porta);
			stub = (RMIHorarioInterf) registro.lookup(nomeObjRemoto);
		} catch (Exception e) {
			System.out
					.println("Cliente: Falha (Exception thrown) ao procurar o"
							+ nomeObjRemoto);
			System.exit(1);
		} // Chama o metodo remoto
		try {
			System.out.println("\n RESPOSTA DO SERVIDOR");
			System.out.println(stub.EscreveMensagem());
		} catch (Exception e) {
			System.out
					.println("Cliente: Falha (Exception thrown) ao chamar EscreveMensagem().");
			System.exit(1);
		}

	}
}
