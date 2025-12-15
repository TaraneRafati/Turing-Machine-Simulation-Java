# Turing Machine Simulation

This repository contains a simple, object-oriented simulation of a **Turing Machine (TM)** implemented in Java. The project serves as a practical demonstration of automata theory concepts for the Theory of Formal Languages and Automata course (2023). 

### Purpose

The provided code simulates the operation of a specific Turing Machine designed to recognize the non-context-free language:

$$L = \{a^n b^n \mid n \ge 1\}$$

The simulation demonstrates the core components of a TM: the tape, the finite set of states, and the deterministic transition function.

### Simulation Details (The $a^n b^n$ Logic)

The simulation uses the following transition logic to match the $a$'s and $b$'s:

1.  **State `q0` (Start):** Finds the leftmost 'a', marks it with 'x', and moves Right to find a 'b'.
2.  **State `q1` (Find 'b'):** Skips over any 'a's or 'y's (previously marked 'b's).
3.  **State `q1` (Mark 'b'):** Finds the leftmost 'b', marks it with 'y', and moves Left to find the 'x' marker.
4.  **State `q2` (Return):** Skips 'a's and 'y's while moving Left until it hits the 'x' marker.
5.  **State `q2` (Loop):** Upon hitting 'x', move Right and repeat the process from `q0`.
6.  **State `q3` (Accept Check):** Once the tape only contains 'y's and the blank symbol 'B', it accepts.
