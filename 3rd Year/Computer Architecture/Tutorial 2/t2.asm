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

;_int64 min(_int64 a, _int64 b, _int64 c) {
;	_int64 v = a;
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

;_int64 p(_int64 i, _int64 j, _int64 k, _int64 l) {
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

;_int64 gcd(_int64 a, _int64 b) {
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

q:			push	rbx ; save rbx (rbx used to remember a+b+c+d+e across call to printf)
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

simple db 'qns\n', 0AH, 00H ; ASCII format string

public qns

qns:
			push	rbx
			sub		rsp, 32 ; allocate shadow space
			lea		rcx, simple ; printf parameter 1 in rcx [&simple]
			call	printf ; call printf
			mov		rax, rbx 
			add		rsp, 32 ; deallocate shadow space
			pop		rbx ; restore rbx
			ret		0 ; return

end