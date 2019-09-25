.686                                ; create 32 bit code
.model flat, C                      ; 32 bit memory model
 option casemap:none                ; case sensitive

.data
public g
g DWORD 4

.code

;
; t1.asm
;

public      min

;int min(int a, int b, int c) {
;	int v = a;
;	if (b < v)
;		v = b;
;	if (c < v)
;		v = c;
;	return v;
;}

min:		push    ebp
            mov     ebp, esp
			mov		eax, [ebp+8]
			cmp		eax, [ebp+12]
			jle		cmpc
			mov		eax, [ebp+12]
cmpc:		cmp		eax, [ebp+16]
			jle		minret
			mov		eax, [ebp+16]
minret:		mov		esp, ebp
			pop		ebp
			ret		0

public		p

;int p(int i, int j, int, k, int l) {
;	return min(min(g, i, j), k, l);
;}

p:			push	ebp
			mov		ebp, esp
			push	[ebp+12]
			push	[ebp+8]
			push	g
			call	min
			add		esp, 12
			
			push	[ebp+20]
			push	[ebp+16]
			push	eax
			call	min
			add		esp, ebp
			
			mov		esp, ebp
			pop		ebp
			ret		0

public		gcd

;int gcd(int a, int b) {
;	if (b == 0) {
;		return a;
;	} else {
;		return gcd(b, a % b);
;	}
;}

gcd:		push	ebp
			mov		ebp, esp
			mov		eax, [ebp+12]
			test	eax, eax
			jne		gcdelse

			mov		eax, [ebp+8]
			jmp		gcdret

gcdelse:	mov		eax, [ebp+8]
			mov		ecx, [ebp+12]
			xor		edx, edx
			idiv	ecx
			push	edx
			push	[ebp+12]
			call	gcd
			add		esp, 8

gcdret:		mov		esp, ebp
			pop		ebp
			ret		0
    
end