package me.ts.aprilfools;

import me.ts.aprilfools.util.ASMUtil;
import me.ts.aprilfools.util.ClassNameUtil;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.*;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class Transformer implements ClassFileTransformer {
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if (!ClassNameUtil.isLunarBaseClass(className)) return classfileBuffer;

        ClassReader cr = new ClassReader(classfileBuffer);
        ClassNode cn = new ClassNode();
        cr.accept(cn, 0);

        for (MethodNode mn : cn.methods) {
            if (mn.desc.equals("(Ljava/time/LocalDate;Ljava/time/Month;)Z")) {
                if (ASMUtil.containsMethodCall(mn, "getAsBoolean")) {
                    InsnList instructions = new InsnList();
                    instructions.add(new InsnNode(Opcodes.ICONST_1));
                    instructions.add(new InsnNode(Opcodes.IRETURN));

                    mn.instructions.clear();
                    mn.localVariables.clear();
                    mn.exceptions.clear();
                    mn.tryCatchBlocks.clear();
                    mn.instructions.add(instructions);

                    ClassWriter cw = new ClassWriter(cr, 0);
                    cn.accept(cw);
                    return cw.toByteArray();
                }
            }
        }

        return classfileBuffer;
    }
}
