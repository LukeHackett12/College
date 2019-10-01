option casemap:none                ; case sensitive

includelib legacy_stdio_definitions.lib
extrn printf:near
.data
public g
g QWORD 4

.code

;
; t2.asm
;

public      min

;_int64 min(int a, int b, int c) {
;	int v = a;
;	if (b < v)
;		v = b;
;	if (c < v)
;		v = c;
;	return v;
;}

min:	    mov		rax, rcx
			cmp		rax, rdx
			jle		cmpc
			mov		rax, rdx
cmpc:		cmp		rax, r8
			jle		minret
			mov		rax, r8
minret:
			ret		0

public		p

;int p(int i, int j, int k, int l) {
;	return min(min(g, i, j), k, l);
;}

p:			sub		rsp, 32
			mov		r12, rcx
			mov		r13, rdx
			mov		r14, r8
			mov		r15, r9

			mov		rcx, g
			mov		rdx, r12
			mov		r8, r13
			call	min

			mov		rcx, rax
			mov		rdx, r14
			mov		r8, r15
			call	min

			add		rsp, 32
			ret		0

public		gcd

;int gcd(int a, int b) {
;	if (b == 0) {
;		return a;
;	} else {
;		return gcd(b, a % b);
;	}
;}

gcd:		mov		rax, rcx
			mov		r10, rdx
			test	r10, r10
			jne		gcdelse
			jmp		gcdret

gcdelse:	sub		rsp, 32
			xor		rdx, rdx
			;cqo
			idiv	r10
			mov		rcx, r10
			call	gcd
			add		rsp, 32
gcdret:		
			ret		0
    
public		q

sums db 'a = %I64d b = %I64d c = %I64d d = %I64d e = %I64d sum = %I64d\n', 0AH, 00H ; ASCII format string

q:			push	rbx ; save rbx (rbx used to remember a+b across call to printf)
			sub		rsp, 56 ; allocate shadow space

			mov		r10, rcx
			mov		r11, rdx
			mov		r12, r8
			mov		r13, r9
			mov		r14, [rsp+104]

			mov		r15, 0
			add		r15, r10
			add		r15, r11
			add		r15, r12
			add		r15, r13
			add		r15, r14

			mov		[rsp+48], r15
			mov		[rsp+40], r14
			mov		[rsp+32], r13
			mov		r9, r12
			mov		r8, r11 ; printf parameter 3 in r8 [b]
			mov		rdx, r10 ; printf parameter 2 in rdx [a]
			lea		rcx, sums ; printf parameter 1 in rcx [&sums]
			mov		rbx, [rsp+48] ; save rsp+48 [a+b+c+d+e] in rbx so preserved across call to printf
			call	printf ; call printf
			mov		rax, rbx ; function result in rax = rbx {a+b+c+d+e}
			add		rsp, 56 ; deallocate shadow space
			pop		rbx ; restore rbx
			ret		0

end