function validateInput(input) {
            input.value = input.value.replace(/\D/g, '').slice(0, 11);
        }

        function validateCpf() {
            const cpfInput = document.getElementById('inputCpf');
            const cpfValue = cpfInput.value;
            const cpfPattern = /^\d{11}$/;
            if (!cpfPattern.test(cpfValue)) {
                alert('Por favor, insira um CPF válido com 11 dígitos.');
                return false;
            }
            return true;
        }

        document.getElementById('inputCpf').addEventListener('keypress', function (e) {
            if (!/^\d$/.test(e.key) || this.value.length >= 11) {
                e.preventDefault();
            }
        });