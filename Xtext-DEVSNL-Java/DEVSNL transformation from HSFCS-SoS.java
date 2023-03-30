

public class IoT_SFS extends AtomicDEVS {
    // Define input and output ports
    public InputPort FlameSensor;
    public InputPort SmokeSensor;
    public OutputPort Sreply;
    public InputPort Srequest;
    
    // Define parameter variables
    public double Trate1;
    public double Trate2;
    public double Trate3;
    public double Trate4;
    
    // Define state variables
    private boolean Fdata;
    private boolean Sdata;
    
    // Constructor
    public IoT_SFS(String name, double Trate1, double Trate2, double Trate3, double Trate4) {
        super();
        this.name = name;
        this.Trate1 = Trate1;
        this.Trate2 = Trate2;
        this.Trate3 = Trate3;
        this.Trate4 = Trate4;
        
        // Define ports
        FlameSensor = new InputPort("FlameSensor", this);
        SmokeSensor = new InputPort("SmokeSensor", this);
        Sreply = new OutputPort("Sreply", this);
        Srequest = new InputPort("Srequest", this);
        
        // Set initial state
        Fdata = false;
        Sdata = false;
    }
    
    // ExtTrans function
    public void extTransfn(double e, Message x) {
        if (x.port == FlameSensor) {
            Fdata = (Boolean) x.value;
        } else if (x.port == SmokeSensor) {
            Sdata = (Boolean) x.value;
        } else if (x.port == Srequest) {
            // Do nothing
        }
    }
    
    // IntTrans function
    public void intTransfn() {
        // Do nothing
    }
    
    // Output function
    public Message out() {
        Message m = new Message();
        if (Fdata) {
            m.port = Sreply;
            m.value = Trate1;
        } else if (Sdata) {
            m.port = Sreply;
            m.value = Trate1;
        } else {
            m = null;
        }
        return m;
    }
    
    // TimeAdvance function
    public double timeAdvancefn() {
        if (Fdata || Sdata) {
            return 0;
        } else {
            return INFINITY;
        }
    }
}
