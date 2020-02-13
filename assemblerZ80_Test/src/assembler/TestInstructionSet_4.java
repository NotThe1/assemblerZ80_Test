package assembler;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
//import static org.hamcrest.CoreMatchers.equalTo;
//import static org.junit.Assert.assertThat;

public class TestInstructionSet_4 {
	InstructionSet is;
	String instruction;
	String subOpCode;
	String arg1, arg2;
	SourceLineAnalyzer analyzer;
	String[] instructions;
	int[] sizes;
	Object[] baseCodes;
	SourceLineParts slp;
	SubInstructionSet subInstructionSet;

	@Before
	public void setUp() throws Exception {
		subInstructionSet = SubInstructionSet.getInstance();
		analyzer = new SourceLineAnalyzer();
		assertThat("handle imports", true, equalTo(true));
		instructions = new String[] { "CCF", "CPD", "CPDR", "CPI", "CPIR", "CPL", "DAA", "DI", "EI", "EXX", "HLT",
				"IND", "INDR", "INI", "INIR", "LDD", "LDDR", "LDI", "LDIR", "NEG", "NOP", "OTDR", "OTIR", "OUTD",
				"OUTI", "RETI", "RETN", "RLA", "RLCA", "RLD", "RRA", "RRCA", "RRD", "SCF" };
		sizes = new int[] { 1, 2, 2, 2, 2, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1, 1, 2, 1,
				1, 2, 1 };

		baseCodes = new Object[] { new byte[] { (byte) 0X3F }, // CCF
				new byte[] { (byte) 0XED, (byte) 0XA9 },	// CPD
				new byte[] { (byte) 0XED, (byte) 0XB9 },	// CPDR
				new byte[] { (byte) 0XED, (byte) 0XA1 },	// CPI
				new byte[] { (byte) 0XED, (byte) 0XB1 },	// CPIR
				new byte[] { (byte) 0X2F },	// CPL
				new byte[] { (byte) 0X27 },	// DAA
				new byte[] { (byte) 0XF3 },	// DI
				new byte[] { (byte) 0XFB },	// EI
				new byte[] { (byte) 0XD9 },	// EXX
				new byte[] { (byte) 0X76 },	// HLT
				new byte[] { (byte) 0XED, (byte) 0XAA },	// IND
				new byte[] { (byte) 0XED, (byte) 0XBA },	// INDR
				new byte[] { (byte) 0XED, (byte) 0XA2 },	// INI
				new byte[] { (byte) 0XED, (byte) 0XB2 },	// INIR
				new byte[] { (byte) 0XED, (byte) 0XA8 },	// LDD
				new byte[] { (byte) 0XED, (byte) 0XB8 },	// LDDR
				new byte[] { (byte) 0XED, (byte) 0XA0 },	// LDI
				new byte[] { (byte) 0XED, (byte) 0XB0 },	// LDIR
				new byte[] { (byte) 0XED, (byte) 0X44 },	// NEG
				new byte[] { (byte) 0X00 },	// NOP
				new byte[] { (byte) 0XED, (byte) 0XBB },	// OTDR
				new byte[] { (byte) 0XED, (byte) 0XB3 },	// OTIR
				new byte[] { (byte) 0XED, (byte) 0XAB },	// OUTD
				new byte[] { (byte) 0XED, (byte) 0XA3 },	// OUTI
				new byte[] { (byte) 0XED, (byte) 0X4D },	// RETI
				new byte[] { (byte) 0XED, (byte) 0X45 },	// RETN
				new byte[] { (byte) 0X17 },	// RLA
				new byte[] { (byte) 0X07 },	// RLCA
				new byte[] { (byte) 0XED, (byte) 0X6F },	// RLD
				new byte[] { (byte) 0X1F },	// RRA
				new byte[] { (byte) 0X0F },	// RRCA
				new byte[] { (byte) 0XED, (byte) 0X67 },	// RRD
				new byte[] { (byte) 0X37 } };	// SCF

	}// setUp

	@Test
	public void testNoArgs() {
		String line;
		String ins;
		instructions = new String[] { "CCF" };
		for (int i = 0; i < instructions.length; i++) {
			ins = instructions[i];
			line = makeLine(ins, "; comment");
			slp = analyzer.analyze(line);
			System.out.println(line);

			assertThat("instruction " + i, instruction, equalTo(slp.getInstruction()));
			subOpCode = slp.getSubOpCode();
			assertThat("subOpCode " + i, instruction + "_0", equalTo(subOpCode));
			assertThat("size " + i, sizes[i], equalTo(slp.getOpCodeSize()));

			assertArrayEquals("Base Codes " + i, (byte[]) baseCodes[i], SubInstructionSet.getBaseCodes(subOpCode));
		}

	}// testCCF
		// --------------------------------------------------------------------------------------------------

	private String makeLine(String instruction, String arg1, String arg2) {
		this.instruction = instruction;
		this.arg1 = arg1;
		this.arg2 = arg2;
		String ans = (this.instruction + " " + this.arg1).trim();
		if (arg2.trim() != "") {
			ans = (this.instruction + " " + this.arg1 + "," + arg2).trim();
		} //
			// System.out.println(ans);
		return ans;
	}// makeLine

	private String makeLine(String instruction, String arg1) {
		return makeLine(instruction, arg1, "");
	}// makeLine

//	private String makeLine(String instruction) {
//		return makeLine(instruction, "", "");
//	}// makeLine
}// class TestInstructionSet_4
