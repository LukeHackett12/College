LIBRARY ieee;
USE ieee.std_logic_1164.ALL;
 
ENTITY ControlMemory256x28_tb IS
END ControlMemory256x28_tb;
 
ARCHITECTURE behavior OF ControlMemory256x28_tb IS 
 
    -- Component Declaration for the Unit Under Test (UUT)
 
    COMPONENT ControlMemory256x28
    PORT(
         in_car : IN  std_logic_vector(7 downto 0);
         MW : OUT  std_logic;
         MM : OUT  std_logic;
         RW : OUT  std_logic;
         MD : OUT  std_logic;
         MB : OUT  std_logic;
         TB : OUT  std_logic;
         TA : OUT  std_logic;
         TD : OUT  std_logic;
         PL : OUT  std_logic;
         PI : OUT  std_logic;
         IL : OUT  std_logic;
         MC : OUT  std_logic;
         FS : OUT  std_logic_vector(4 downto 0);
         MS : OUT  std_logic_vector(2 downto 0);
         NA : OUT  std_logic_vector(7 downto 0)
        );
    END COMPONENT;
    

   --Inputs
   signal in_car : std_logic_vector(7 downto 0) := (others => '0');

 	--Outputs
   signal MW : std_logic;
   signal MM : std_logic;
   signal RW : std_logic;
   signal MD : std_logic;
   signal MB : std_logic;
   signal TB : std_logic;
   signal TA : std_logic;
   signal TD : std_logic;
   signal PL : std_logic;
   signal PI : std_logic;
   signal IL : std_logic;
   signal MC : std_logic;
   signal FS : std_logic_vector(4 downto 0);
   signal MS : std_logic_vector(2 downto 0);
   signal NA : std_logic_vector(7 downto 0);
 
BEGIN
 
	-- Instantiate the Unit Under Test (UUT)
   uut: ControlMemory256x28 PORT MAP (
          in_car => in_car,
          MW => MW,
          MM => MM,
          RW => RW,
          MD => MD,
          MB => MB,
          TB => TB,
          TA => TA,
          TD => TD,
          PL => PL,
          PI => PI,
          IL => IL,
          MC => MC,
          FS => FS,
          MS => MS,
          NA => NA
        );

   -- Stimulus process
   stim_proc: process
   begin		
		wait for 10ns;
		in_car <= x"00";
				
		wait for 10ns;
		in_car <= x"08";
				
		wait for 10ns;
		in_car <= x"0E";
		
		wait;
   end process;

END;
