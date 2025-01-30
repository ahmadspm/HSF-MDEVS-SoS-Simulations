package my.package.HSFSOSDSL;

/**
 * Class representing an IoT System with Flame and Smoke Sensors.
 */
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
    
    /**
     * Constructor for IoT_SFS.
     * 
     * @param name Name of the IoT system
     * @param Trate1 Transmission rate 1
     * @param Trate2 Transmission rate 2
     * @param Trate3 Transmission rate 3
     * @param Trate4 Transmission rate 4
     */
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
    
    /**
     * External transition function.
     * 
     * @param e Elapsed time since last event
     * @param x Incoming message
     */
    public void extTransfn(double e, Message x) {
        if (x.port == FlameSensor) {
            Fdata = (Boolean) x.value;
        } else if (x.port == SmokeSensor) {
            Sdata = (Boolean) x.value;
        } else if (x.port == Srequest) {
            // Do nothing
        }
    }
    
    /**
     * Internal transition function.
     */
    public void intTransfn() {
        // Do nothing
    }
    
    /**
     * Output function.
     * 
     * @return Message generated as output
     */
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
    
    /**
     * Time advance function.
     * 
     * @return Time until next event
     */
    public double timeAdvancefn() {
        if (Fdata || Sdata) {
            return 0;
        } else {
            return INFINITY;
        }
    }
    
    /**
     * Method to handle warning messages.
     * 
     * @param message Warning message
     * @param args Additional arguments
     */
    private void warning(String message, Object... args) {
        // Implementation for warning messages
    }

    /**
     * Method to handle error messages.
     * 
     * @param message Error message
     * @param args Additional arguments
     */
    private void error(String message, Object... args) {
        // Implementation for error messages
    }
}
