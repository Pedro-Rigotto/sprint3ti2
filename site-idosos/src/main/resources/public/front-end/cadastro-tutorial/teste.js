formInsert += "<h1>Cadastro de tutorial</h1>\n";
formInsert += "<form id=\"form-contato\" method=\"post\" action=\"/criartutorial/insert/do\">\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-2\">\n";
formInsert += "            <label for=\"inputId\">Id</label>\n";
formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputId\" placeholder=\"ID\" name=\"id\" disabled>\n";
formInsert += "        </div>\n";
formInsert += "        <div class=\"col-sm-10\">\n";
formInsert += "            <label for=\"inputTitulo\">Título (*)</label>\n";
formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputTitulo\" required name=\"titulo\" placeholder=\"Informe o nome do título\">\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-12\">\n";
formInsert += "            <label for=\"inputURL\">URL do Vídeo (*)</label>\n";
formInsert += "            <input type=\"url\" class=\"form-control\" id=\"inputURL\" required name=\"link\" placeholder=\"Informe a URL do vídeo\">\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-12\">\n";
formInsert += "            <label for=\"inputTutorial\">Texto do Tutorial</label>\n";
formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputTutorial\" name=\"texto\" placeholder=\"Informe o texto do tutotial\" >\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-12\">\n";
formInsert += "            <label for=\"inputCategoria\">Categoria (*)</label>\n";
formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputCategoria\" name=\"categoria\" placeholder=\"Informe a ID da categoria\">\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-4\">\n";
formInsert += "            <small>(*) Campos obrigatórios</small>\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-12\">\n";
formInsert += "            <input type=\"submit\" class=\"btn btn-secondary\" id=\"btnInserir\" value=\"Inserir\">\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "</form>\n";






formInsert += "<h1>Atualização de tutorial</h1>\n";
formInsert += "<form id=\"form-contato\" method=\"post\" action=\"/criartutorial/update/do\">\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-2\">\n";
formInsert += "            <label for=\"inputId\">Id</label>\n";
formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputId\" placeholder=\"ID\" name=\"id\">\n";
formInsert += "        </div>\n";
formInsert += "        <div class=\"col-sm-10\">\n";
formInsert += "            <label for=\"inputTitulo\">Título (*)</label>\n";
formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputTitulo\" required name=\"titulo\" placeholder=\"Informe o nome do título\">\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-12\">\n";
formInsert += "            <label for=\"inputURL\">URL do Vídeo (*)</label>\n";
formInsert += "            <input type=\"url\" class=\"form-control\" id=\"inputURL\" required name=\"link\" placeholder=\"Informe a URL do vídeo\">\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-12\">\n";
formInsert += "            <label for=\"inputTutorial\">Texto do Tutorial</label>\n";
formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputTutorial\" name=\"texto\" placeholder=\"Informe o texto do tutotial\" >\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-12\">\n";
formInsert += "            <label for=\"inputCategoria\">Categoria (*)</label>\n";
formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputCategoria\" name=\"categoria\" placeholder=\"Informe a ID da categoria\">\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-4\">\n";
formInsert += "            <small>(*) Campos obrigatórios</small>\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-12\">\n";
formInsert += "            <input type=\"submit\" class=\"btn btn-secondary\" id=\"btnAtualizar\" value=\"Alterar\">\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "</form>\n";





formInsert += "<h1>Remoção de tutorial</h1>\n";
formInsert += "<form id=\"form-contato\" method=\"post\" action=\"/criartutorial/insert/do\">\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-2\">\n";
formInsert += "            <label for=\"inputId\">Id</label>\n";
formInsert += "            <input type=\"text\" class=\"form-control\" id=\"inputId\" placeholder=\"ID\" name=\"id\" disabled>\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "    <div class=\"form-group row\">\n";
formInsert += "        <div class=\"col-sm-12\">\n";
formInsert += "            <input type=\"button\" class=\"btn btn-secondary\" id=\"btnExcluir\" value=\"Excluir\">\n";
formInsert += "        </div>\n";
formInsert += "    </div>\n";
formInsert += "</form>\n";