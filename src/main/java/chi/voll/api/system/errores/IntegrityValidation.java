package chi.voll.api.system.errores;

public class IntegrityValidation extends RuntimeException {
    public IntegrityValidation(String s) { super(s); }
}
