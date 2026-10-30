import java.nio.file.*;
import java.util.*;
import org.objectweb.asm.*;
import org.objectweb.asm.tree.*;

/** Adds an optional alternative pack using the original Fabric registration sequence. */
public class AddBuiltinPack {
    public static void main(String[] args) throws Exception {
        Path input = Path.of(args[0]);
        ClassNode owner = new ClassNode();
        new ClassReader(Files.readAllBytes(input)).accept(owner, 0);
        if (owner.methods.stream().anyMatch(m -> m.name.equals("registerAlternativePack")))
            throw new IllegalStateException("Already patched");
        MethodNode init = owner.methods.stream().filter(m -> m.name.equals("onInitialize")).findFirst().orElseThrow();
        MethodNode pack = new MethodNode(Opcodes.ACC_PRIVATE | Opcodes.ACC_STATIC,
                "registerAlternativePack", "()V", null, null);
        boolean found = false;
        for (AbstractInsnNode insn : init.instructions) {
            if (insn.getOpcode() < 0) continue;
            AbstractInsnNode copy = insn.clone(new HashMap<>());
            if (copy instanceof LdcInsnNode literal) {
                if (literal.cst.equals("programmer_art")) literal.cst = "alternative";
                if (literal.cst.equals("Wood Stairs (Programmer Art)")) literal.cst = "Wood Stairs (Alternative)";
            }
            pack.instructions.add(copy);
            if (insn instanceof MethodInsnNode call && call.name.equals("registerBuiltinPack")) {
                found = true;
                break;
            }
        }
        if (!found) throw new IllegalStateException("Original registration not found");
        // The registration result is not needed by this optional pack.
        pack.instructions.add(new InsnNode(Opcodes.POP));
        pack.instructions.add(new InsnNode(Opcodes.RETURN));
        owner.methods.add(pack);
        init.instructions.insert(new MethodInsnNode(Opcodes.INVOKESTATIC, owner.name, pack.name, pack.desc, false));
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        owner.accept(writer);
        Files.write(Path.of(args[1]), writer.toByteArray());
    }
}



