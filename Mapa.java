import java.util.Scanner;

public class Mapa {
	
	static char[][] labirinto;
	
	static char[][] mapaMedio;
	
	static int jogadorX, jogadorY;
  
    static int saidaX, saidaY;
    
    static int maxMovimentos;
    
    static int movimentosRealizados;
    
    static int maxVisualizacoes;
    
    static int visualizacoesUsadas;
    
    static int pontuacao;
    
    static int itensColetados;
	
	static Scanner Ler = new Scanner(System.in);
	
	static Boolean jogar = true;
	
	
	
	
	
	public static void main(String[] args) {
		
		movimentosRealizados = 0;
        visualizacoesUsadas = 0;
        pontuacao = 0;
        itensColetados = 0;
		
		
		while (jogar) {
			
		System.out.println("---------------------------------------------------");
		System.out.println("Seja bem-vindo(a) ao labirinto!");
		System.out.println(" (1) - Jogar ");
		System.out.println(" (2) - Regras ");
		System.out.println(" (3) - Painel de créditos ");
		System.out.println(" (4) - Sair ");
		System.out.println("---------------------------------------------------");
		
			String opçãoin = Ler.nextLine();
		
		switch (opçãoin) {
		case "1":
			jogo();
			break;
		case "2":
			regras();
			break;
		case "3":
			creditos();
			break;
		case "4":
			System.out.println("TCHAU!!!!");
			jogar = false;
			break;
		default:
			System.out.println("Valor inválido! Digite um número de 1 a 4.");
			 jogar = true;
		}
	}
		
		
		
		
		
	}
		public static void jogo() {
			
			System.out.println("----------------------------------------------------");
			System.out.println(" Selecione a dificuldade pelo número correspondente:");
			System.out.println(" (1) - Fácil -> 10x10 ");
			System.out.println(" (2) - Médio -> 20x20 ");
			System.out.println(" (3) - Difícil -> 30x30 ");
			System.out.println(" (4) - Voltar ao menu ");
			System.out.println("----------------------------------------------------");
			
			String opçãoint = Ler.nextLine();
			
			switch (opçãoint) {
			
			case "1":
				mapaFacil();
				break;
				
			case "2":
				mapaMedio();
				break;
			
			case "3":
				mapaDificil();
				break;
			
			case "4":
				jogar = true;
              
				break;
              
			default:
				System.out.println("Valor inválido! Digite um número de 1 a 4.");
				jogo();
				
		}
	        boolean jogoAtivo = true;
	        while(jogoAtivo) {
	            System.out.println("\n=== MENU ===");
	            System.out.println("1. Mover (W/A/S/D)");
	            System.out.println("2. Visualizar labirinto (" + (maxVisualizacoes - visualizacoesUsadas) + " restantes)");
	            System.out.println("3. Sair do jogo");
	            System.out.print("Escolha: ");
	            String opcao = Ler.nextLine();
	            
	            switch(opcao) {
	                case "1":
	                    System.out.print("Digite a direção (W/A/S/D): ");
	                    char direcao = Ler.next().toUpperCase().charAt(0);
	                    moverJogador(direcao);
	                    break;
	                case "2":
	                    if(visualizacoesUsadas < maxVisualizacoes) {
	                        exibirLabirinto();
	                        visualizacoesUsadas++;
	                        System.out.println("Visualizações usadas: " + visualizacoesUsadas + "/" + maxVisualizacoes);
	                    } else {
	                        System.out.println("Você já usou todas as visualizações disponíveis!");
	                    }
	                    break;
	                case "3":
	                    jogoAtivo = false;
	                    System.out.println("Você desistiu do jogo!");
	                    break;
	                default:
	                    System.out.println("Valor inválido! Escolha um número de 1 a 3.");
	            }
	            
	            if(jogadorX == saidaX && jogadorY == saidaY) {
	                jogoAtivo = false;
	                System.out.println("\nPARABÉNS! Você encontrou a saída!");
	                calcularPontuacao();
	                exibirResultadoFinal();
	            } else if(movimentosRealizados >= maxMovimentos) {
	                jogoAtivo = false;
	                System.out.println("\nFIM DE JOGO! Você esgotou seus movimentos!");
	                exibirResultadoFinal();
	            }
	        }
	        
	        
	    }
	    
		public static void regras() {
			
			System.out.println("-----------------------------------------------------------------------------------------------------------------------");
			System.out.println("Seja bem-vindo(a) às regras do labirinto!");
			System.out.println("- Você terá uma quantidade limitada de movimentos e visualizações do labirinto ");
			System.out.println("- No meio do labirinto, haverão itens coletáveis que valem pontos, "
					+ "ou aumentam o número de visualizações permitidas ");
			System.out.println("- Caso o número de movimentos acabe, você perde ");
			System.out.println("- Caso você saia do labirinto, você perde  ");
			System.out.println(" () - Digite (1) para voltar para o menu");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------");
			String voltarmenu = Ler.nextLine();
			
			if (voltarmenu.equals("1") ) {
				jogar = true;
			} else { 
				System.out.println("Valor inválido!");
				regras();
			}
		}
		
		public static void creditos() {
			System.out.println("Participantes do Projeto:");
			System.out.println("-----------------------------------------------------------------------------------------------------------------------");
			System.out.println();
			System.out.println("Antonio Fernando Alcantara Neto");
			System.out.println("Enzo Amaral Santos");
			System.out.println("José Rafael Leite");
			System.out.println("Pedro Paulo Costa da Silva");
			System.out.println("Yago Sá Lobão");
			System.out.println("Yuri Pereira Vieira Evandro");
			System.out.println();
			System.out.println("-----------------------------------------------------------------------------------------------------------------------");
			System.out.println("Digite (1) para ir para o menu");
			System.out.println("Digite (2) para sair do programa");

			
			
			String opcaocred = Ler.nextLine();
			if ( opcaocred.equals("1") ) {
				jogar = true;
			} else { 
				if(opcaocred.equals("2")) {
					jogar = false ;
				} else {
				System.out.println("Valor inválido! Digite um número de 1 a 2.");
				creditos();
				}
			}
		}
			public static void mapaFacil() {
				System.out.println("Gostaria de personalizar o seu jogo? Digite (1) para sim ou (2) para não ");
				  String opcao = Ler.nextLine();
				 switch (opcao) {
				 case "1":
					 System.out.print("Digite o número máximo de movimentos: ");
				        maxMovimentos = Ler.nextInt();
				        
				        System.out.print("Digite o número máximo de visualizações do labirinto: ");
				        maxVisualizacoes = Ler.nextInt();
				 break;
				 
				 case "2":
					 maxMovimentos = 20;
					 maxVisualizacoes = 10;
					 break;
					 
					 default:
						 System.out.println("Valor inválido! Digite um número de 1 a 2.");
						 mapaFacil();
					 break;
				 }
				 labirinto = new char[][] {
			            {'P', '.', '.', '#', '#', '.', '.', '.', '.', '.'},
			            {'#', '#', '.', '#', '.', '.', '#', '#', '#', '.'},
			            {'.', '.', '.', '.', '#', '.', '.', '.', '#', '.'},
			            {'.', '#', '#', '.', '#', '#', '#', '.', '.', '#'},
			            {'.', '#', '.', '.', '.', '.', '.', '.', '#', '#'},
			            {'.', '#', '.', '#', '#', '#', '.', '#', '#', '.'},
			            {'.', '.', '.', '#', '.', '.', '.', '.', '.', '.'},
			            {'#', '#', '.', '.', '.', '#', '#', '.', '#', '.'},
			            {'.', '.', '#', '#', '.', '.', '#', '.', '.', '.'},
			            {'.', '.', '.', '.', '#', '#', '#', '#', '#', 'S'}
			        };
			        
			        labirinto[1][5] = '*';
			        labirinto[4][4] = '*';
			        labirinto[7][3] = '*';
			        encontrarPosicoesIniciais();
			    }	
		public static void mapaMedio() {
			System.out.println("Gostaria de personalizar o seu jogo? Digite (1) para sim ou (2) para não ");
			  String opcao = Ler.nextLine();
			 switch (opcao) {
			 case "1":
				 System.out.print("Digite o número máximo de movimentos: ");
			        maxMovimentos = Ler.nextInt();
			        
			        System.out.print("Digite o número máximo de visualizações do labirinto: ");
			        maxVisualizacoes = Ler.nextInt();
			 break;
			 
			 case "2":
				 maxMovimentos = 50;
				 maxVisualizacoes = 10;
				 break;
				 
				 default:
					 System.out.println("Valor inválido! Digite um número de 1 a 2.");
					 mapaMedio();
				 break;
			 }
			 labirinto = new char[][] {
					    {'#','.','.','.','#','#','#','.','.','.','.','.','#','#','#','.','.','.','#','#'},
					    {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','S'},
					    {'.','.','#','#','#','#','#','.','#','#','#','#','#','#','#','#','#','#','.','.'},
					    {'.','.','#','.','.','.','#','.','.','.','.','.','.','.','.','.','#','#','.','.'},
					    {'.','.','#','.','#','.','#','#','#','#','#','#','#','.','#','.','.','#','.','.'},
					    {'#','.','#','.','#','.','*','.','.','.','.','.','#','.','#','.','#','#','.','#'},
					    {'#','.','#','.','#','#','#','#','#','#','.','.','#','.','#','.','.','.','.','#'},
					    {'#','.','#','.','.','.','.','.','.','#','.','.','#','#','#','#','#','.','.','#'},
					    {'.','.','#','#','#','#','#','#','.','#','.','.','.','.','#','.','#','#','#','#'},
					    {'.','.','.','.','.','.','.','#','.','#','#','#','.','#','#','.','.','.','#','.'},
					    {'#','#','#','#','#','.','.','#','.','.','.','#','.','.','.','.','#','.','#','.'},
					    {'.','.','.','.','#','.','#','#','#','#','.','#','#','#','.','#','#','.','#','.'},
					    {'.','.','#','#','#','.','.','.','.','#','.','.','.','.','#','.','.','.','#','.'},
					    {'.','.','#','.','.','#','#','.','#','.','#','#','#','#','.','#','#','#','#','#'},
					    {'.','#','#','#','#','#','#','.','#','#','#','#','#','.','.','.','.','.','.','.'},
					    {'.','P','.','.','.','.','.','.','.','.','.','.','.','#','#','#','#','#','.','.'},
					    {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','.','.','.','.','.'},
					    {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
					    {'.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
					    {'#','.','.','.','#','#','#','.','#','#','.','.','.','#','#','#','#','.','.','.'}
					};
					labirinto[8][15] = '*';
					labirinto[1][9] = '*';
					labirinto[8][4] = '*';
					labirinto[19][7] = '*';
				    encontrarPosicoesIniciais();
		}
		public static void mapaDificil() {
			System.out.println("Gostaria de personalizar o seu jogo? Digite (1) para sim ou (2) para não ");
			  String opcao = Ler.nextLine();
			 switch (opcao) {
			 case "1":
				 System.out.print("Digite o número máximo de movimentos: ");
			        maxMovimentos = Ler.nextInt();
			        
			        System.out.print("Digite o número máximo de visualizações do labirinto: ");
			        maxVisualizacoes = Ler.nextInt();
			 break;
			 
			 case "2":
				 maxMovimentos = 140;
				 maxVisualizacoes = 10;
				 break;
				 
				 default:
					 System.out.println("Valor inválido! Digite um número de 1 a 2.");
					 mapaDificil();
				 break;
				
			 }
			 labirinto = new char[][] {
					    {'#','.','.','.','.','#','#','#','#','.','.','.','#','#','.','.','#','.','.','.','.','#','#','#','.','.','.','.','#','.'},
					    {'.','P','.','.','#','.','.','.','.','.','.','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.'},
					    {'.','#','#','.','#','.','#','#','#','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.'},
					    {'#','.','.','.','#','.','#','.','.','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','.'},
					    {'#','.','#','#','#','.','#','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','.'},
					    {'#','.','#','.','.','.','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','.','#','.','.'},
					    {'.','.','#','.','#','#','#','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','#'},
					    {'.','.','#','.','#','.','.','.','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','.','#','.','.','#'},
					    {'.','.','#','.','#','.','#','#','#','.','#','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','#','.','#'},
					    {'.','.','#','.','#','.','.','.','.','.','#','.','#','#','#','#','#','#','#','#','#','.','#','.','#','.','.','.','.','#'},
					    {'.','.','#','#','#','#','#','#','#','#','#','.','#','.','.','.','.','.','.','.','#','.','#','.','#','#','#','#','.','.'},
					    {'#','.','.','.','.','.','.','.','.','.','.','.','#','.','#','#','#','#','#','.','#','.','#','.','.','.','.','#','.','.'},
					    {'#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','.','.','.','#','.','#','.','#','#','#','.','#','.','#','.'},
					    {'#','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','.','#','.','.','#','.','.','.','.','.','#'},
					    {'#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','.','#','.','#','#','.','#','.','#','#','#','.','#'},
					    {'#','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','.','.','.','.','.','.','.','#','.','.'},
					    {'#','.','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','#','#','#','#','#','#','#','.','#','.','.'},
					    {'#','.','#','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','.'},
					    {'#','.','#','.','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','.','#','.','.','.'},
					    {'#','.','#','.','#','.','#','.','.','.','.','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','#','.','.'},
					    {'.','.','#','.','#','.','#','.','#','#','#','#','#','#','#','#','#','#','#','#','#','.','#','.','#','.','.','.','.','#'},
					    {'.','.','#','.','#','.','#','.','#','.','.','.','.','.','.','.','.','.','.','.','#','.','#','.','#','#','#','#','.','#'},
					    {'#','.','#','.','#','.','#','.','#','.','#','#','#','#','#','#','#','#','#','.','#','.','#','.','.','.','.','#','.','#'},
					    {'#','.','#','.','#','.','#','.','#','.','#','.','.','.','.','.','.','.','#','.','#','.','#','#','#','.','#','.','#','#'},
					    {'#','.','#','.','#','.','#','.','#','.','#','.','#','#','#','#','.','.','#','.','#','.','.','.','.','.','.','.','.','#'},
					    {'.','.','#','.','#','.','#','.','#','.','#','.','#','.','.','#','.','.','#','.','#','#','#','#','.','#','#','#','.','.'},
					    {'.','.','#','.','#','.','#','.','#','.','#','.','#','.','#','#','.','.','#','.','.','.','.','.','.','.','.','.','.','.'},
					    {'.','.','#','.','#','.','#','.','#','.','#','.','#','.','#','.','.','.','#','#','#','#','#','.','#','#','#','#','.','.'},
					    {'#','.','#','.','#','.','#','.','#','.','#','.','#','.','#','.','#','#','#','.','.','.','.','.','.','.','.','#','.','#'},
					    {'#','.','.','.','#','.','#','.','#','.','#','.','#','.','#','.','.','.','.','#','#','#','#','#','#','.','S','#','.','#'},
			 };
					labirinto[11][26] = '*';
					labirinto[13][4] = '*';
					labirinto[17][2] = '*';
					labirinto[22][29] = '*';
			 		encontrarPosicoesIniciais();
		}
		 public static void encontrarPosicoesIniciais() {
		        for(int i = 0; i < labirinto.length; i++) {
		            for(int j = 0; j < labirinto[i].length; j++) {
		                if(labirinto[i][j] == 'P') {
		                    jogadorY = i;
		                    jogadorX = j;
		                    labirinto[i][j] = '@';
		                } else if(labirinto[i][j] == 'S') {
		                    saidaY = i;
		                    saidaX = j;
		                }
		            }
		        }
		    }
		 public static void moverJogador(char direcao) {
		        int novoX = jogadorX;
		        int novoY = jogadorY;
		        
		        switch(direcao) {
		            case 'W': 
		                novoY--;
		                break;
		            case 'A':
		                novoX--;
		                break;
		            case 'S': 
		                novoY++;
		                break;
		            case 'D': 
		                novoX++;
		                break;
		            default:
		                System.out.println("Direção inválida! Use apenas W, A, S ou D.");
		                return;
		        }
		        
		        if(novoX < 0 || novoX >= labirinto.length || novoY < 0 || novoY >= labirinto[0].length) {
		            System.out.println("Movimento inválido! Você não pode sair do labirinto.");
		        } else if(labirinto[novoY][novoX] == '#') {
		            System.out.println("Movimento inválido! Há uma parede no caminho.");
		        } else {
		            labirinto[jogadorY][jogadorX] = '.'; 
		            
		            if(labirinto[novoY][novoX] == '*') {
		                itensColetados++;
		                pontuacao += 50;
		                System.out.println("Você coletou um item! +50 pontos");
		            }
		            
		            jogadorX = novoX;
		            jogadorY = novoY;
		            labirinto[jogadorY][jogadorX] = '@'; 
		            
		            movimentosRealizados++;
		            System.out.println("Movimentos realizados: " + movimentosRealizados + "/" + maxMovimentos);
		        }
		    }
		   public static void exibirLabirinto() {
		        System.out.println("\n=== LABIRINTO ===");
		        for(int i = 0; i < labirinto.length; i++) {
		            for(int j = 0; j < labirinto[i].length; j++) {
		                System.out.print(labirinto[i][j] + " ");
		            }
		            System.out.println();
		        }
		    }
		   public static void calcularPontuacao() {
		        int movimentosRestantes = maxMovimentos - movimentosRealizados;
		        pontuacao += movimentosRestantes * 10;
		        pontuacao += (maxVisualizacoes - visualizacoesUsadas) * 20;
	}
		   public static void exibirResultadoFinal() {
		        System.out.println("\n=== RESULTADO FINAL ===");
		        System.out.println("Movimentos realizados: " + movimentosRealizados);
		        System.out.println("Visualizações usadas: " + visualizacoesUsadas);
		        System.out.println("Itens coletados: " + itensColetados);
		        System.out.println("Pontuação final: " + pontuacao);
		   }
 }