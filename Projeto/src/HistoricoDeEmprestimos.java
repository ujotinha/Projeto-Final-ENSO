import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class HistoricoDeEmprestimos {
    private double multa = 0;
    private ArrayList<Emprestimo> emprestimos_realizados = new ArrayList<Emprestimo>();
    private Calendar c = Calendar.getInstance();
    private int ano = c.get(Calendar.YEAR);
    private int mes = (c.get(Calendar.MONTH) + 1);
    private int dia_do_mes = c.get(Calendar.DAY_OF_MONTH);
    Scanner leia = new Scanner(System.in);

    // Getter e Setters
    public double getMulta() {
        return multa;
    }

    public void setMulta(double multa) {
        this.multa = multa;
    }

    public void aplicarMulta(double multa) {
        this.multa += multa;
    }

    public ArrayList<Emprestimo> getEmprestimos_realizados() {
        return emprestimos_realizados;
    }

    public void setEmprestimos_realizados(ArrayList<Emprestimo> emprestimos_realizados) {
        this.emprestimos_realizados = emprestimos_realizados;
    }
}