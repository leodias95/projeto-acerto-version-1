package acerto.executavel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Acerto {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		float salario_bruto, porcentagem_fgts, fgts, tempo_de_servico, acertoSalarial, qtddia, semaviso, semfgts;
		System.out.println("Informe o valor do bruto do salário: ");
		salario_bruto = scanner.nextFloat();
		System.out.println("Para a base de cálculos informe  a porcentagem atual do FGTS");
		fgts = scanner.nextFloat();
		porcentagem_fgts = fgts / 100;
		System.out.println("Informe em meses quanto tempo o funcionário permaneceu na empresa em meses");
		tempo_de_servico = scanner.nextFloat();
		scanner.nextLine();
		System.out.println("A demissão foi por justa causa? Sim ou Não");
		String demissao = scanner.nextLine();
		System.out.println("O funcionário compriu o aviso prévio? Sim ou Não");
		String aviso_previo = scanner.nextLine();
		acertoSalarial = ((salario_bruto / 12) * tempo_de_servico) + (salario_bruto / 3)+((salario_bruto * porcentagem_fgts)*tempo_de_servico);
		semfgts = ((salario_bruto / 12) * tempo_de_servico) + (salario_bruto / 3);
		if (demissao.equals("Não") && aviso_previo.equals("Sim")) {
			System.out.println("Sem o FGTS o funcionário irá receber o valor de R$ " + semfgts + " pelo o seu tempo na empresa");
			System.out.println("Com o FGTS o funcionário irá receber o valor de R$ " + acertoSalarial + " pelo o seu tempo na empresa");

		} else {
			System.out.println("Informe em dias quanto tempo o funcionário trabalhou depois que pediu demissão. Sendo 0 para nenhum dia cumprido");
			qtddia = scanner.nextFloat();
			semaviso = ((salario_bruto / 30) * qtddia);
			System.out.println("O funcionário irá receber R$ " + semaviso + " por seus dias trabalhados");
		}
		String gerarpdf="";
		System.out.println("Deseja gerar um pdf? Sim ou Não");
		gerarpdf = scanner.nextLine();
		Document document = new Document();
		if (gerarpdf.equals("Sim")) {
			try {
				PdfWriter.getInstance(document, new FileOutputStream("Acerto.pdf"));
				document.open();
				document.add(new Paragraph("Salário Bruto ---------- R$ " + salario_bruto));
				document.add(new Paragraph("FGTS ------------------- R$ " + (porcentagem_fgts * salario_bruto * tempo_de_servico)));
				document.add(new Paragraph("Férias ----------------- R$ " + ((salario_bruto / 12) * tempo_de_servico)));
				document.add(new Paragraph("1/3 Férias ------------- R$ " + (salario_bruto / 3)));
				document.add(new Paragraph("Total sem FGTS --------- R$ " + semfgts));
				document.add(new Paragraph("Total com FGTS --------- R$ " + acertoSalarial));
			} catch (DocumentException de) {
				System.err.println(de.getLocalizedMessage());
			} catch (IOException ioe) {
				System.err.println(ioe.getMessage());

			}
			document.close();
			System.exit(0);
		} else {
			System.out.println("Aplicação finalizada");
			System.exit(0);
		}
	}
}
