package me.ts.aprilfools.util;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

public class ASMUtil {
    public static boolean containsMethodCall(MethodNode methodNode, String name) {
        for (AbstractInsnNode insn : methodNode.instructions) {
            if (insn instanceof MethodInsnNode) {
                MethodInsnNode methodInsn = (MethodInsnNode) insn;
                if (methodInsn.name.equals(name)) {
                    return true;
                }
            }
        }
        return false;
    }
}
